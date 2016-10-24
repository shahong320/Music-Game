package ChatRoomAction;

import java.net.Socket;

import ChatRoomClient.ChatContext;
import GameHallClient.Request;
import GameHallClient.Response;
import GameHallClient.ServerAction;
import GameHallClient.User;
import GameHallClient.XStreamUtil;

/**
 * When a user goin the chatroom
 * @author Hongzhou Sha
 *
 */
public class UserInAction implements ServerAction{

	@Override
	public void execute(Request request, Response response, Socket socket) {
		// TODO Auto-generated method stub
		//get the user who goin
		User user = (User)request.getParameter("user");
		//Set user Socet
		user.setSocket(socket);
		// put on contect
		ChatContext.users.put(user.getId(), user);
		//tell user and start chatroom and send user infromation
		response.setData("users", ChatContext.users);
		user.getPrintStream().println(XStreamUtil.toXML(response));
		//tell all of users there is a new user comein.
		String receiveUserInAction = (String)request.getParameter("userInAction");
		//dispose new user comein action
		response.setActionClass(receiveUserInAction);
		response.setData("newUser", user);
		//send for all of users
		for (String key : ChatContext.users.keySet()) {
			User user1 = ChatContext.users.get(key);
			if (user1.getId() != user.getId()) {
				user1.getPrintStream().println(XStreamUtil.toXML(response));
			}
		}
	}

}
