package UIFrame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.newdawn.slick.SlickException;
import ChatRoomClient.ChatRoom;
import GameHallClient.User;
import slick2d.HelloWorld;
import slick2d.JPane;


public class MainUI extends JFrame{
	public ChatRoom chat;
	public JPane jp;
	private User user1;
	private List<User> users1;
	public HelloWorld hw;
	public MainUI(User user,List<User> users) throws SlickException{
	
		

		this.user1 = user;
		this.users1 = users;
		this.setSize(1600, 930);
		chat = new ChatRoom(this.user1,this.users1);
		this.add(chat);
		this.setLayout(null);
		chat.setBounds(-40, 475, 870, 830);
    	this.setLocation(10, 50);
		this.setVisible(true);	
		jp = new JPane ();
		this.add(jp);
		jp.setBounds(800, 0,400, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
			User user = new User();
		
		//	user.setHeadImage("images/heads/3.gif");
			
			List<User> users = new ArrayList<User>();
			
				new MainUI(user,users);
			
	}

}
