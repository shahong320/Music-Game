package ChatRoomAction;

import ChatRoomClient.ClienContext;
import GameHallClient.ClientAction;
import GameHallClient.Response;
import GameHallClient.User;
import UIFrame.MainUI;
// Obtain new user comein action
public class NewUserInAction implements ClientAction{

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		User newUser = (User)response.getData("newUser");
		MainUI mu = ClienContext.mainFrame;
		mu.chat.addUser(newUser);
		
	}

}
