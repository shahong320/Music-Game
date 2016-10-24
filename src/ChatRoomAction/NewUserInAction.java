package ChatRoomAction;

import ChatRoomClient.ClienContext;
import GameHallClient.ClientAction;
import GameHallClient.Response;
import GameHallClient.User;
import UIFrame.MainFrame;

/**
 *  Obtain new user comein action
 * @author HongzhouSha
 *
 */
public class NewUserInAction implements ClientAction{

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		User newUser = (User)response.getData("newUser");
		MainFrame mainUI = ClienContext.mainFrame;
		mainUI.chat.addUser(newUser);
		
	}

}
