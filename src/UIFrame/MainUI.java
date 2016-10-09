package UIFrame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.newdawn.slick.SlickException;
import ChatRoomClient.ChatRoom;
import GameHallClient.User;
import slick2d.HelloWorld;



public class MainUI extends JFrame{
	public ChatRoom chat;
	private JPane jp;
	private User user1;
	private List<User> users1;
	private PanelUserOne panelUserOne;
	//public HelloWorld hw;
	

	public MainUI(User user,List<User> users) throws SlickException{
		this.user1 = user;
		this.users1 = users;
		this.setSize(1600, 730);
		
		panelUserOne = new PanelUserOne(this.user1,this.users1);
		this.add(panelUserOne);
		this.setLayout(null);
		panelUserOne.setBounds(-50, 0, 300, 200);
		
		chat = new ChatRoom(this.user1,this.users1);
		this.add(chat);	
		this.setLayout(null);
		chat.setBounds(-40, 275, 870, 830);
		
	
		
    	this.setLocation(10, 50);
		this.setVisible(true);	
	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jp = new JPane ();
		this.add(jp);
		jp.setBounds(800, 0,400, 600);
		
	}
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
			User user = new User();
		
		//	user.setHeadImage("images/heads/3.gif");
			
			List<User> users = new ArrayList<User>();
			
				new MainUI(user,users);
			
	}

}
