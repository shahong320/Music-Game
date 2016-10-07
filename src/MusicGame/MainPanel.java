import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * This class is a main panel for switching different panels(HomeScreen, History, etc)
 * This class initializes all necessary panels, and has Actionlistener for all buttons of these
 * panels.
 */
public class MainPanel extends JPanel implements ActionListener {
	private HomeScreen home;
	private Trophy trophyPanel;
	private GameHistory history;

	/*
	 * Initialization of panel and add button listener
	 */
	public MainPanel() {
		// Initialize
		super(new BorderLayout());

		home = new HomeScreen();
		trophyPanel = new Trophy();
		history = new GameHistory();

		this.add(home);

		// Button listener
		home.getSingleGame().addActionListener(this);
		home.getMultiPlay().addActionListener(this);
		home.getTrophy().addActionListener(this);
		home.getGameHistory().addActionListener(this);
		home.getExit().addActionListener(this);
		home.getTrophy().addActionListener(this);
		trophyPanel.getBack().addActionListener(this);
		history.getBack().addActionListener(this);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainPanel main = new MainPanel();
		JFrame frame = new JFrame("Music Game");
		frame.add(main);
		Dimension screen = frame.getToolkit().getScreenSize();
		frame.setSize(screen.width / 2, screen.height / 2);
		frame.setLocation(frame.getWidth() / 2, frame.getHeight() / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/*
	 * Button Actionlistener performance
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == home.getSingleGame()) {
			//Start game single
		} else if (source == home.getMultiPlay()) {
			//Start game multiPlay
		} else if (source == home.getTrophy()) {
			//Switch to Trophy panel
			this.removeAll();
			this.add(trophyPanel);
			this.repaint();
			this.revalidate();
		} else if (source == home.getGameHistory()) {
			//Switch to GameHistory panel
			this.removeAll();
			this.add(history);
			this.repaint();
			this.revalidate();
		} else if (source == home.getExit()) {
			System.exit(0);
		} else if (source == trophyPanel.getBack() || source == history.getBack()) {
			//Switch to HomeScreen panel
			this.removeAll();
			this.add(home);
			this.repaint();
			this.revalidate();
		}
	}
}
