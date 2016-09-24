import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener{
	private HomeScreen home;
	private Trophy trophyPanel;
	private GameHistory history;
	
	public MainPanel(){
		//Initialize
		super(new BorderLayout());
		
		home=new HomeScreen();
		trophyPanel=new Trophy();
		history=new GameHistory();
		
		this.add(home);
		
        
        //Button listener
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
		MainPanel main=new MainPanel();
		JFrame frame=new JFrame("Music Game");
		frame.add(main);
		Dimension screen = frame.getToolkit().getScreenSize();
		frame.setSize(screen.width/2,screen.height/2);
		frame.setLocation(frame.getWidth()/2, frame.getHeight()/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if(source==home.getStartGame()){
			
		}
		else if(source==home.getMultiPlay()){
			
		}
		else if(source==home.getTrophy()){
			this.removeAll();
			this.add(trophyPanel);
			this.repaint();
			this.revalidate();
		}
		else if(source==home.getGameHistory()){
			this.removeAll();
			this.add(history);
			this.repaint();
			this.revalidate();
		}
		else if(source==home.getExit()){
			System.exit(0);
		}
		else if(source==trophyPanel.getBack() || source==history.getBack()){
			this.removeAll();
			this.add(home);
			this.repaint();
			this.revalidate();
		}
	}
}
