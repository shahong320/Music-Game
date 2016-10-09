package GameClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.Socket;
import java.util.UUID;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GameHallClient.Game;
import GameHallClient.User;






public class LogIn extends JFrame{
	//Create the User Name.
	private JLabel nameTextLabel = new JLabel("User Name£º");
	private JTextField nameField = new JTextField(20);
	//Select the sex.
	private JLabel sexTextLabel = new JLabel("Gender£º");
	private JComboBox sexSelect = new JComboBox();
	//Select the Game.
	private JLabel gameTextLabel = new JLabel("Select Game£º");
	private JComboBox gameSelect = new JComboBox();
	//Connect the IP address.
	private JLabel connectionLabel = new JLabel("IP address£º");
	private JTextField connectionField = new JTextField("127.0.0.1");
	//Create buttons.
	private JButton YesButton = new JButton("Yes");
	private JButton NoButton = new JButton("No");
	
	private User user;
	

	@SuppressWarnings("unchecked")
	public LogIn(){
		this.sexSelect.addItem("Male");
		this.sexSelect.addItem("Female");
		GameSelect();
		Box nameBox = Box.createHorizontalBox();
		nameBox.add(Box.createHorizontalStrut(30));
		nameBox.add(this.nameTextLabel);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(this.nameField);
		nameBox.add(Box.createHorizontalStrut(30));
		
		Box sexBox = Box.createHorizontalBox();
		sexBox.add(Box.createHorizontalStrut(30));
		sexBox.add(this.sexTextLabel);
		sexBox.add(Box.createHorizontalStrut(43));
		sexBox.add(this.sexSelect);
		sexBox.add(Box.createHorizontalStrut(170));
		

		Box gameBox = Box.createHorizontalBox();
		gameBox.add(Box.createHorizontalStrut(30));
		gameBox.add(this.gameTextLabel);
		gameBox.add(Box.createHorizontalStrut(10));
		gameBox.add(this.gameSelect);
		gameBox.add(Box.createHorizontalStrut(30));
		
		Box connectionBox = Box.createHorizontalBox();
		connectionBox.add(Box.createHorizontalStrut(30));
		connectionBox.add(this.connectionLabel);
		connectionBox.add(Box.createHorizontalStrut(23));
		connectionBox.add(this.connectionField);
		connectionBox.add(Box.createHorizontalStrut(30));
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(this.YesButton);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(this.NoButton);
		
		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(nameBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(sexBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(connectionBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(gameBox);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(buttonBox);
		mainBox.add(Box.createVerticalStrut(20));
		this.add(mainBox);
		this.setTitle("Log In Game");
     	this.setLocation(800, 300);
		this.pack();
		this.setVisible(true);
		initializeListeners();
		this.user = new User();
	

	}
	//initialize listenners
	private void initializeListeners() {
			this.YesButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						login();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			this.NoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
	}
	private void login() throws Exception {
			if (this.nameField.getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "Plese Enter Your Name", "warning", 
						JOptionPane.OK_CANCEL_OPTION);
				return;
			}
			//Set User's detail
			setUser();
			this.user.setSocket(createSocket(this.connectionField.getText(), 12000));
			//Botain the game from seletion
			Game game = (Game)this.gameSelect.getSelectedItem();
			game.start(this.user);
			//Start Thread
			ClientThread thread = new ClientThread(this.user);
			thread.start();
			this.setVisible(false);
	}
	
	
	private void GameSelect() {
		readGame();
	}
	
	private Socket createSocket(String address, int port) throws Exception {
		try {
			//Create Socket
			return new Socket(address, port);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private void setUser() {
		//Create User
		int sex = getSex();
		String name = this.nameField.getText();
		String id = UUID.randomUUID().toString();
		this.user.setId(id);
		this.user.setName(name);
		this.user.setSex(sex);
	}
	
	private int getSex() {
		String sex = (String)this.sexSelect.getSelectedItem();
		if (sex.equals("Male")) return 0;
		else return 1;
	}
	

	//Read the MANIFEST.MF under the Game, read the Game-Class attributes, if it exist then slide.
	private void readGame() {
		try {
			File file = new File("game");
			for (File file1 : file.listFiles()) {
				if (CheckJar(file1.getName())) {
					//get jar
					JarFile jar = new JarFile(file1);
					//get manifest
					Manifest mf = jar.getManifest();
					//get attributes
					Attributes gameClassAttrs = mf.getMainAttributes();
					//find game class attributes
					String gameClass = gameClassAttrs.getValue("Game-Class");
					if (gameClass != null) {
						Game game = (Game)Class.forName(gameClass).newInstance();
						this.gameSelect.addItem(game);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check the file is Jar or not.
	 * @param fileName
	 * @return true or false.
	 */
	private boolean CheckJar(String fileName) {
		int pointIndex = fileName.lastIndexOf(".");
		if (pointIndex != -1) {
			String subfix = fileName.substring(pointIndex + 1, fileName.length());
			if ("jar".equals(subfix)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LogIn();
	}

}
