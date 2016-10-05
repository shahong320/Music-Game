package ChatRoomAction;

import java.net.Socket;

import ChatRoomClient.ChatContext;
import GameHallClient.Request;
import GameHallClient.Response;
import GameHallClient.ServerAction;
import GameHallClient.User;
import GameHallClient.XStreamUtil;

public class UserSendAction implements ServerAction{

	@Override
	public void execute(Request request, Response response, Socket socket) {
		// TODO Auto-generated method stub
		String content = (String)request.getParameter("content");
		String receiverId = (String)request.getParameter("receiverId");
		String senderId = (String)request.getParameter("senderId");
		//Obtain sender
		User sender = ChatContext.users.get(senderId);
		if ("all".equals(receiverId)) {
			//send to all
			sendToAll(content, sender, response);
		} else {
			//send to one
			sendToOne(content, receiverId, sender, response);
		}
	}
	//send to all
	private void sendToAll(String content, User sender, Response response) {
		response.setData("senderName", sender.getName());
		for (String key : ChatContext.users.keySet()) {
			User user1 = ChatContext.users.get(key);
			if (user1.getId() != sender.getId()) {
				user1.getPrintStream().println(XStreamUtil.toXML(response));
			}
		}
	}
	//send to one
	private void sendToOne(String content, String receiverId, User sender, Response response) {
		User receive = ChatContext.users.get(receiverId);
		response.setData("senderName", sender.getName());
		receive.getPrintStream().println(XStreamUtil.toXML(response));
	}
}
