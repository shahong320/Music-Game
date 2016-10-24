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
		//tell server new user comein.
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
