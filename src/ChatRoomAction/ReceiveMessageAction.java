package ChatRoomAction;


import ChatRoomClient.ClienContext;
import GameHallClient.ClientAction;
import GameHallClient.Response;
import UIFrame.MainUI;

//Reveive message Action
public class ReceiveMessageAction implements ClientAction {

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		String content = (String)response.getData("content");
		String senderName = (String)response.getData("senderName");
		MainUI mu = ClienContext.mainFrame;
		mu.chat.appendContent(senderName + " says to you: " + content);
	}

}
