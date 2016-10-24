package ChatRoomAction;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.SlickException;

import ChatRoomClient.ClienContext;
import GameHallClient.ClientAction;
import GameHallClient.Response;
import GameHallClient.User;
import UIFrame.MainUI;

public class StartAction implements ClientAction{
	/**
	 * Change the Map to the List
	 * @param usersMap
	 * @return
	 */
	private List<User> getUser(Map<String, User> usersMap) {
		List<User> resultList = new ArrayList<User>();
		for (String key : usersMap.keySet()) {
			User user = usersMap.get(key);
			resultList.add(user);
		}
		return resultList;
	}

	public void execute(Response response) {
		Map<String, User> usersMap = (Map<String, User>)response.getData("users");
		List<User> users = getUser(usersMap);
		MainUI ChatFrame = null;
		try {
			ChatFrame = new MainUI(ClienContext.user, users);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClienContext.mainFrame = ChatFrame;
	}
	
}
