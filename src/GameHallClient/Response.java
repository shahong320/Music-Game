package GameHallClient;
import java.util.HashMap;
import java.util.Map;

public class Response {
	//errorCode
	private String errorCode;
	//Servrt retuns datas.
	private Map<String, Object> datas;
	
	//update the data in request's map
	private String actionClass;
	
	public Response(String actionClass) {
		this.datas = new HashMap<String, Object>();
		this.actionClass = actionClass;
	}
	
	public void setData(String key, Object value) {
		this.datas.put(key, value);
	}
	
	public Object getData(String key) {
		return this.datas.get(key);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

}
