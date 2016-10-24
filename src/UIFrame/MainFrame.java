package UIFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.newdawn.slick.SlickException;

import ChatRoomClient.ChatRoom;
import GameHallClient.User;
import GamePackage.Container;
import Panel.GamePanel;
import Panel.MainPanel;

/**
 * This is the Main frame with main method to set up panels 
 * and start this application
 *
 */


public class MainFrame extends JFrame{
	public ChatRoom chat;
	private GamePanel jp;
	private User user1;
	private List<User> users1;
	private MainPanel mainPanel;
	
	

	public MainFrame(User user,List<User> users) throws SlickException{
		this.user1 = user;
		this.users1 = users;
		this.setSize(1000, 700);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)screen.getWidth()/2 - this.getWidth()/2, (int)screen.getHeight()/2 - this.getHeight()/2);
		this.setResizable(false);
		
		JPanel userJPanel =new JPanel();

		
		chat = new ChatRoom(this.user1,this.users1);

		JPanel leftPanel=new JPanel();
		leftPanel.add(userJPanel);
		leftPanel.add(chat);
		
		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

		
		jp = new GamePanel ();

		JPanel rightPanel=new JPanel();
		rightPanel.add(jp);
		
	
		
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel,BorderLayout.CENTER);

		this.add(mainPanel);
		this.setVisible(true);	
		
		jp.setFrame(this);
		jp.start();
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new Close());
	}

	public void exit (){
		this.dispose();
	}



	class Close implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
			User user = new User();
		
		//	user.setHeadImage("images/heads/3.gif");
			
			List<User> users = new ArrayList<User>();
			
				new MainFrame(user,users);	
			
	}
}