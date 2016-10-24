package Panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.SlickException;

import UIFrame.MainFrame;
import GamePackage.Container;


public class GamePanel  extends JPanel{
	private JLabel panelLabel;
//	private Container gameContainer;
	CanvasGameContainer mainContainer;
// store the main frame 
	private MainFrame mainUI;
		
	public GamePanel() throws SlickException{
	//	this.infoLabel = new JLabel("Your Name: ");
		Container gameContainer = new Container("LoveLive");
		mainContainer = new CanvasGameContainer (gameContainer);
		mainContainer.setSize(600, 700);
//		Box infoBox = Box.createHorizontalBox();
//		infoBox.add(app);
//		Box sendBox = Box.createHorizontalBox();
//		sendBox.add(this.sendButton);
//		
//		Box contentBox = Box.createVerticalBox();
//		contentBox.add(infoBox);
//		contentBox.add(sendBox);		
		this.add(mainContainer);
		gameContainer.setMainPanel(this);
		//app.start();
	}
	/*
	public Container container (){
		return gameContainer;
	}
	*/
	public void start (){
		try {
			mainContainer.start();
			mainContainer.getContainer().setTargetFrameRate(120);
		} catch (SlickException e) {
		
			e.printStackTrace();
		}
	}
	
	public void setFrame (MainFrame ui){
		this.mainUI = ui;
	}
	
	public MainFrame getFrame (){
		return mainUI;
	}
	
}
