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
	private PrintStream printstream;
	private Socket socket;
	private String line;
	private BufferedReader buffreder;

	//keep the action object which was created.
	public Map<String, ServerAction> actions = new HashMap<String, ServerAction>();
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	//set the map of request in the response
	private void copyParameters(Request request, Response response) {
		Map<String, Object> parameters = request.getParameters();
		for (String key : parameters.keySet()) {
			response.setData(key, parameters.get(key));
		}
	}
	//get the action from map if not create.
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
	//array to xml
	private Request getRequest(String xml) {
		
		try {
			Request request = (Request)XStreamUtil.fromXML(xml);
			return request;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//server action to xml
	private String getResponseXML(Response response) {
		return XStreamUtil.toXML(response);
	}
	@SuppressWarnings("unused")
	public void run() {
		try {
			this.buffreder = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			while((this.line = buffreder.readLine()) != null) {
				//get request
				Request request = getRequest(this.line);
				//bulit request object
				Response response = new Response(request.getClientAction());
				//set infro in request
				copyParameters(request, response);
				//if is not request object and return.
				if (request == null) {
					response.setErrorCode("request error");
					this.printstream = new PrintStream(socket.getOutputStream());
					this.printstream.println(getResponseXML(response));
					break;
				}
				//get server
				ServerAction action = getAction(request.getServerAction());
				//excute action if cant find reutn
				if (action == null) {
					response.setErrorCode("could not find any commands");
					this.printstream = new PrintStream(socket.getOutputStream());
					this.printstream.println(getResponseXML(response));
				} else {
					action.execute(request, response, this.socket);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	

	

}
