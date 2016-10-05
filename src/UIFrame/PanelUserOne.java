package UIFrame;

import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ChatRoomClient.ChatRoom;
import GameHallClient.User;

public class PanelUserOne extends JPanel{
	/**
	 * The panel of User one.
	 */
	private static final long serialVersionUID = 1L;
	private JLabel GamePane;
	private JLabel UserName;
	private JLabel UserSex;
	private JLabel UserPoint = null;
	private JLabel PlayerOne;
	private MainUI chat;
	private String sex;
	private List<User> users1;
	public PanelUserOne(User user,List<User> users){
		if(user.getSex()==0){
			this.sex ="Male";
		}else
			this.sex="Female";
		this.users1 = users;	
	
		UserName = new JLabel("User One Name: "+this.users1.get(0).getName());
		UserSex = new JLabel("User One Sex: "+this.users1.get(0).getSex());
		UserPoint = new JLabel();
		PlayerOne = new JLabel("--The Player One Infromation--");
		Box Player = Box.createHorizontalBox();
		Player.add(PlayerOne);
	
		
		Box NameBox = Box.createHorizontalBox();
		NameBox.add(UserName);
	
		Box SexBox = Box.createHorizontalBox();
		SexBox.add(UserSex);
	
		Box PointBox = Box.createHorizontalBox();
		PointBox.add(UserPoint);
		
		Box contentBox = Box.createVerticalBox();

		contentBox.add(Player);
		contentBox.add(Box.createVerticalStrut(10));
		contentBox.add(NameBox);
		contentBox.add(Box.createVerticalStrut(10));
		contentBox.add(SexBox);
		contentBox.add(Box.createVerticalStrut(10));
		contentBox.add(PointBox);
		
		this.add(contentBox);
		new UserScore().start();
	
		
	}
	
	class UserScore  extends Thread{
		public UserScore(){}
		private int i =0;
		@Override
     	public void run() {
			for(;;){
				UserPoint.setText("User One Score: "+i);
				i++;
				try {
     				Thread.sleep(1000);
     			} catch (InterruptedException e) {
     				e.printStackTrace();
     			}
			}
			
		}
	}
}











