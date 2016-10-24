package ChatRoomAction;


import ChatRoomClient.ClienContext;
import GameHallClient.ClientAction;
import GameHallClient.Response;
import UIFrame.MainFrame;


/**
 * Reveive message Action
 * @author HongzhouSha
 *
 */
public class ReceiveMessageAction implements ClientAction {

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		String content = (String)response.getData("content");
		String senderName = (String)response.getData("senderName");
		MainFrame mainUI = ClienContext.mainFrame;
		mainUI.chat.appendContent(senderName + " says to you: " + content);
	}

}
