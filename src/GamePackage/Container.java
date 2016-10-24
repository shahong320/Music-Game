package GamePackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import Panel.GamePanel;

public class Container extends StateBasedGame{	
	private Selection selection;
	private GameInterface game;
	private UserInform user;
	private GamePanel panel;
	
	public Container(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		selection = new Selection(GameState.Start.getValue());
		game = new GameInterface(GameState.Start.getValue());
		this.addState(selection);
		this.addState(game);
		linkInterface();
		user = new ReadSave().readSave();
	}
	
	public UserInform getUser (){
		return user;
	}
	
	public void setUpdateUser (UserInform user){
		this.user = user;
	}
	
	public void linkInterface (){
		selection.setContianer(this);
		game.setContianer(this);
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.getState(GameState.Selection.getValue());
		this.getState(GameState.Game.getValue());
		this.enterState(GameState.Selection.getValue());
	}
	
	public GameInterface getGame(){
		return game;
	}
	
	public void setMainPanel (GamePanel panel){
		this.panel = panel;
	}
	
	public GamePanel getPanel (){
		return panel;
	}
	
	public static void main (String args[]){
		try {
			JFrame f = new JFrame("Happy BirthDay Miku!");
			Container game = new Container("LoveLive!");
			CanvasGameContainer app = new CanvasGameContainer (game);
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();		
			f.add(app);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.addWindowListener(new Close());
			f.setSize(600, 700);
			f.setResizable(false);
			f.setLocation((int)screen.getWidth()/2 - f.getWidth()/2, (int)screen.getHeight()/2 - f.getHeight()/2);
			f.setVisible(true);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
