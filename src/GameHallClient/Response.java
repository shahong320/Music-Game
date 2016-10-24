package GameHallClient;
import java.util.HashMap;
import java.util.Map;

public class Response {
	//errorCode
	private String error;
	//update the data in request's map
	private String actionClass;
	//Servrt retuns datas.
	private Map<String, Object> data;
	
	
	public Response(String actionClass) {
		this.data = new HashMap<String, Object>();
		this.actionClass = actionClass;
	}
	
	public void setData(String key, Object value) {
		this.data.put(key, value);
	}
	
	public Object getData(String key) {
		return this.data.get(key);
	}

	public String getErrorCode() {
		return error;
	}

	public void setErrorCode(String errorCode) {
		this.error = errorCode;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

}
