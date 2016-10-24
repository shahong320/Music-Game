package GameHallClient;

import java.util.HashMap;
import java.util.Map;



public class Request {
	private String serverAction;
	//Map
	private Map<String, Object> parameters;
		
	private String clientAction;

	public Request(String serverAction, String clientAction) {
		this.serverAction = serverAction;
		this.parameters = new HashMap<String, Object>();
		this.clientAction = clientAction;
	}
	
	public Map<String, Object> getParameters() {
		return this.parameters;
	}
	
	public void setParameter(String key, Object value) {
		this.parameters.put(key, value);
	}
	
	public Object getParameter(String key) {
		return this.parameters.get(key);
	}

	public String getServerAction() {
		return serverAction;
	}

	public void setServerAction(String serverAction) {
		this.serverAction = serverAction;
	}

	public String getClientAction() {
		return clientAction;
	}

	public void setClientAction(String clientAction) {
		this.clientAction = clientAction;
	}

	public String toXML() {
		return XStreamUtil.toXML(this);
	}
}
