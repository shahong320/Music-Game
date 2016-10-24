package ChatRoomClient;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import GameHallClient.User;
import GameHallClient.XStreamUtil;
import GameHallClient.Request;


public class ChatRoom extends JPanel{
	//display the content of chat
	private JTextArea textArea = new JTextArea(20, 70);
	//Enter details 
	private JTextField field = new JTextField(20);
	//Send button
	private JButton sendButton = new JButton("Send");
	//current user
	private User user;
	//Chat room list
	private JList list;
	//SelfInfro JLabel
	private JLabel infoLabel;
	private List<User> users;
	
	private User allUser = new User();
	
	
	public ChatRoom(User user,List<User> users){
		this.user = user;
		this.users = users;
		this.users.add(0,this.allUser);
		this.allUser.setName("All User");
		this.allUser.setId("all");
		//remover yourself form list
	    removeSelf();
		this.infoLabel = new JLabel("Your Name: "+user.getName());
		//Create user list
		createUserList();
		this.textArea.setEditable(false);	
		JScrollPane content = new JScrollPane(this.textArea);
		content.setMinimumSize(new Dimension(400,200));
		
		Box infoBox = Box.createHorizontalBox();
		infoBox.add(this.infoLabel);
		
		Box sendBox = Box.createHorizontalBox();
		sendBox.add(this.field);
		sendBox.add(this.sendButton);
		
		
		Box contentBox = Box.createVerticalBox();
		contentBox.add(infoBox);
		contentBox.add(content);
		contentBox.add(sendBox);

		this.sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		
		this.add(contentBox);

	}
	//create user list
	private void createUserList() {
		this.list = new JList();
		this.list.setListData(this.users.toArray());
		this.list.setFixedCellHeight(40);
		this.list.setCellRenderer(new UserListCellRenderer());
	}
	//refresh list
	private void refresh() {
		this.list.setListData(this.users.toArray());
	}
	//remove self from list.
		private void removeSelf() {
			for (Iterator it = this.users.iterator(); it.hasNext();) {
				User u = (User)it.next();
				if (u.getId().equals(this.user.getId())) {
					it.remove();
				}
			}
		}

	//Send Messages
		private void send() {
			User selectUser = (User)this.list.getSelectedValue();
			if (selectUser == null) {
				selectUser = this.allUser;
			}
			Request request = new Request("ChatRoomAction.UserSendAction", 
					"ChatRoomAction.ReceiveMessageAction");
			request.setParameter("content", this.field.getText());
			request.setParameter("senderId", this.user.getId());
			request.setParameter("receiverId", selectUser.getId());
			appendContent("You say to "+selectUser.getName()+":"+this.field.getText());
			this.user.getPrintStream().println(XStreamUtil.toXML(request));
			
		}
		
		public void appendContent(String content) {
			if (this.textArea.getText().equals("")) this.textArea.append(content);
			else this.textArea.append("\n" + content);
		}

		//add new user
		public void addUser(User newUser) {	
			this.users.add(newUser);
			appendContent(newUser.getName() + " Come in");
			refresh();
		}
		
	/**
	 * main test.=============================================	
	 */
//		public static void main(String[] args) {
//			
//			User user = new User();
//		
//	
//			
//			List<User> users = new ArrayList<User>();
//			User u1 = new User();
//		
//			u1.setName("user1");
//			User u2 = new User();
//			u2.setName("user2");
//		
//			users.add(u1);
//			users.add(u2);
//			 new ChatRoom(user, users);
//		}
		
}
