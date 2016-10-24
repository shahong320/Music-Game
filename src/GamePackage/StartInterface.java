package GamePackage;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.lwjgl.input.Mouse;

public class StartInterface extends BasicGameState {
	private Image backGround;
	private Image title;
	private ImageButton start;
	private Container container;
	MusicPlayer player;

	public StartInterface(int state) {

	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		backGround = new Image("res/58039478_p0.png");
		title = new Image("res/title1.png");
		start = new ImageButton ("res/start1.png", 50, 400);
//		selection = new Selection(GameState.Selection.getValue());
//		game.setSong("AudioFile/StartDash.wav", 150, "AudioFile/StartDash2.chart");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// TODO Auto-generated method stub
		arg2.drawImage(backGround, 0, 0);
		arg2.drawImage(title, 50, 50);
		start.draw();
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		if (player == null){
			player = new MusicPlayer ("AudioFile/bip.wav");
			player.Start_Loop();
		}
/*		if ((Mouse.getX() > 50 && Mouse.getX() < 50 + start.getWidth()) && (Mouse.getY() < arg0.getHeight() - 400
				&& Mouse.getY() > arg0.getHeight() - 400 - start.getHeight())) {
			if (Mouse.isButtonDown(0)) {
				player.stop();
				arg1.enterState(GameState.Game.getValue());
			}
		}*/
		if (start.isClicked(arg0)){
			player.stop();
			player = null;
			arg1.enterState(GameState.Selection.getValue());
		}
	}

	public void setContianer (Container container){
		this.container = container;
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return GameState.Start.getValue();
	}

}
