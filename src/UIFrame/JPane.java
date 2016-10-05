package UIFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.SlickException;
import MusicGameStart.Game;



public class JPane  extends JPanel{
	private JLabel GamePane;
	private Game MG;
	
	public JPane() throws SlickException{
	//	this.infoLabel = new JLabel("Your Name: ");
		MG = new Game("LoveLive");
		CanvasGameContainer app = new CanvasGameContainer (MG);
		app.setSize(800, 600);
//		Box infoBox = Box.createHorizontalBox();
//		infoBox.add(app);
//		Box sendBox = Box.createHorizontalBox();
//		sendBox.add(this.sendButton);
//		
//		Box contentBox = Box.createVerticalBox();
//		contentBox.add(infoBox);
//		contentBox.add(sendBox);		
		this.add(app);
		app.start();
	}
	
	
}
