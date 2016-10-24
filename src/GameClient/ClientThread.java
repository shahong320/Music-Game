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
	private Map<String, ClientAction>  actionMap = new HashMap<String, ClientAction>();
	
	public ClientThread(User user){
		this.user = user;
	}
	
	public void run(){
		try {
			InputStream tream = this.user.getSocket().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(tream));
			while ((this.string = reader.readLine()) != null) {
				Response response = getResponse(this.string);
				ClientAction action = getClientAction(response.getActionClass());
				action.execute(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//get client action
	private ClientAction getClientAction(String className) {
		try {
			if (actionMap.get(className) == null) {
				ClientAction action = (ClientAction)Class.forName(className).newInstance();
				actionMap.put(className, action);
			}
			return actionMap.get(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//change the xml to response obejct
	private Response getResponse(String xml) {
		return (Response)XStreamUtil.fromXML(xml);
	}
}
