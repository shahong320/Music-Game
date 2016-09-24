package GameClient;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import GameHallClient.ClientAction;
import GameHallClient.Response;
import GameHallClient.User;
import GameHallClient.XStreamUtil;






public class ClientThread extends Thread{
	private String string;
	private User user;
	private Map<String, ClientAction>  actions = new HashMap<String, ClientAction>();
	
	public ClientThread(User user){
		this.user = user;
	}
	
	public void run(){
		try {
			InputStream istream = this.user.getSocket().getInputStream();
			BufferedReader breader = new BufferedReader(new InputStreamReader(istream));
			while ((this.string = breader.readLine()) != null) {
				Response response = getResponse(this.string);
				ClientAction action = getClientAction(response.getActionClass());
				action.execute(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//得到服务器响应中的客户端处理类
	private ClientAction getClientAction(String className) {
		try {
			if (actions.get(className) == null) {
				ClientAction action = (ClientAction)Class.forName(className).newInstance();
				actions.put(className, action);
			}
			return actions.get(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//将服务器响应的xml转换成Response对象
	private Response getResponse(String xml) {
		return (Response)XStreamUtil.fromXML(xml);
	}
}
