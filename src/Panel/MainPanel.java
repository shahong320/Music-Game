package Panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.newdawn.slick.SlickException;

import GameClient.LogIn;
import GameHallClient.User;
import GameHallServer.MainOfServer;
import GameHallServer.Server;
import GamePackage.MusicPlayer;
import UIFrame.MainFrame;

/**
 * This class is a main panel for switching different panels(HomeScreen,
 * History, etc) This class initializes all necessary panels, and has
 * Actionlistener for all buttons of these panels.
 */
public class MainPanel extends JPanel implements ActionListener {
	private HomePagePanel homePanel;
	private TrophyPanel trophyPanel;
	private JFrame mainFrame;
	private MainFrame mainPanel;
	private MainOfServer mainServer;
	private LogIn loginFrame;
	private User user = new User();
	private List<User> users = new ArrayList<User>();
	private MusicPlayer player;

	/**
	 * Initialization of panel and add button listener
	 */
	public MainPanel() {
		// Initialize
		super(new BorderLayout());
		mainFrame = new JFrame("Music Game");

		homePanel = new HomePagePanel();
		trophyPanel = new TrophyPanel();
		
		player = new MusicPlayer ("AudioFile/BlueStar.wav");
		// history = new GameHistory();

		this.add(homePanel);

		player.Start_Loop();
		
		// Button listener
		homePanel.getSingleGame().addActionListener(this);
		homePanel.getMultiPlay().addActionListener(this);
		homePanel.getTrophy().addActionListener(this);
		homePanel.getExit().addActionListener(this);
		homePanel.getTrophy().addActionListener(this);
		trophyPanel.getBack().addActionListener(this);

		// add the main Panel to Main Frame
		mainFrame.add(this);

		// set the performance of frame
		Dimension screen = mainFrame.getToolkit().getScreenSize();
		mainFrame.setSize(1000, 700);
		mainFrame.setLocation(
				(int) screen.getWidth() / 2 - mainFrame.getWidth() / 2,
				(int) screen.getHeight() / 2 - mainFrame.getHeight() / 2);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	/**
	 * Button Actionlistener performance
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == homePanel.getSingleGame()) {
			// Start game single
			try {
				mainPanel = new MainFrame(user, users);
				player.stop();
				player = null;
				mainFrame.dispose();
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
			}

		} else if (source == homePanel.getMultiPlay()) {
			// Start game multiPlay
			mainServer = new MainOfServer();
			mainServer.start();
			loginFrame = new LogIn();
			player.stop();
			player = null;
			mainFrame.dispose();

		} else if (source == homePanel.getTrophy()) {
			// Switch to Trophy panel
			this.removeAll();
			this.add(trophyPanel);
			this.repaint();
			this.revalidate();
		} else if (source == homePanel.getExit()) {
			System.exit(0);
		} else if (source == trophyPanel.getBack()) {
			// Switch to HomeScreen panel
			this.removeAll();
			this.add(homePanel);
			this.repaint();
			this.revalidate();
		}
	}

	public static void main(String[] args) {

		MainPanel main = new MainPanel();
	}
}
