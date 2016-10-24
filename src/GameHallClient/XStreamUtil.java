package GameHallClient;
import com.thoughtworks.xstream.XStream;

public class XStreamUtil {
private static XStream xstream = new XStream();
	
	/**
	 * XMLtransition
	 * @param xml
	 * @return xstream.fromXML(xml)
	 */
	public static Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}
	
	/**
	 * transition for String
	 * @param obj
	 * @return s
	 */
	public static String toXML(Object obj) {
		String xml = xstream.toXML(obj);
		String a = xml.replaceAll("\n", "");
		String s = a.replaceAll("\r", "");
		return s;
	}


}
