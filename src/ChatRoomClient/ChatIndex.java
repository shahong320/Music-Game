package ChatRoomClient;



import GameHallClient.Game;
import GameHallClient.Request;
import GameHallClient.User;
import GameHallClient.XStreamUtil;

public class ChatIndex implements Game{

	@Override
	public void start(User user) {
		// TODO Auto-generated method stub
		ClienContext.user = user;
		//进入聊天室, 告诉服务器用户有用户进入
		Request request = new Request("ChatRoomAction.UserInAction", 
				"ChatRoomAction.StartAction");
		request.setParameter("userInAction", 
				"ChatRoomAction.NewUserInAction");
		request.setParameter("user", user);
		user.getPrintStream().println(XStreamUtil.toXML(request));
	}
	
	public String toString() {
		return "Music Game";
	}
	

}
