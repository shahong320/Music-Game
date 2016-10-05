package GameHallServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import GameHallClient.Request;
import GameHallClient.Response;
import GameHallClient.ServerAction;
import GameHallClient.XStreamUtil;


public class ServerThread extends Thread{
	private Socket socket;
	
	private BufferedReader br;
	
	private String line;
	
	private PrintStream ps;

	//���汻������Action����
	public Map<String, ServerAction> actions = new HashMap<String, ServerAction>();
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			while((this.line = br.readLine()) != null) {
				//�õ��������
				Request request = getRequest(this.line);
				//��request�еõ��ͻ��˴�����, ���ҹ���Response����
				Response response = new Response(request.getClientAction());
				//������Ĳ��������õ�Response��
				copyParameters(request, response);
				//����ַ�������ת����Request����, �����ô����벢����
				if (request == null) {
					response.setErrorCode("request error");
					this.ps = new PrintStream(socket.getOutputStream());
					this.ps.println(getResponseXML(response));
					break;
				}
				//�õ�Server������
				ServerAction action = getAction(request.getServerAction());
				//����Ҳ�����Ӧ��Action, ���ش�����Ϣ, �ҵ���ִ��Action
				if (action == null) {
					response.setErrorCode("could not find any commands");
					this.ps = new PrintStream(socket.getOutputStream());
					this.ps.println(getResponseXML(response));
				} else {
					action.execute(request, response, this.socket);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��Request�еĲ���map���õ�Response��data��
	private void copyParameters(Request request, Response response) {
		Map<String, Object> parameters = request.getParameters();
		for (String key : parameters.keySet()) {
			response.setData(key, parameters.get(key));
		}
	}
	
	//��һ�η�������Ӧת����XML�ַ���
	private String getResponseXML(Response response) {
		return XStreamUtil.toXML(response);
	}
	
	//���ַ���ת����һ��Request����
	private Request getRequest(String xml) {
		try {
			Request r = (Request)XStreamUtil.fromXML(xml);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//��Map�еõ�Action����, ����ò���, �򴴽�
	private ServerAction getAction(String className) {
		try {
			if (actions.get(className) == null) {
				ServerAction action = (ServerAction)Class.forName(className).newInstance();
				actions.put(className, action);
			}
			return actions.get(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}