package GamePackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Challenge.Challenge;


public class GameInterface extends BasicGameState{
	private MusicPlayer player;	
	private int baseHight;
	private int baseWidth;
	private GameContainer container;
	private Container gameContainer;
	private DrawComponent drawingComp;
	private CheckAction checking;
	private long stop;
	private Input input;
	private Song song;
	private ArrayList<Node> chars;
	private String path;
	private Score score;
	private boolean showMenue = false;
	
	public GameInterface (int state){
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) {
		// TODO Auto-generated method stub
		input = arg0.getInput();
		container = arg0;
		baseHight = arg0.getHeight();
		baseWidth = arg0.getWidth();
		drawingComp = new DrawComponent();
		score = new Score ();
	}
	
	public void readCharFile (String path){		
		this.path = path;
		chars = new ChartFileParser ().getChartFromFile(path);		
	}
	
	public void setSong (Song song){
		this.song = song;
		readCharFile ("AudioFile/" + song.getSongName() + ".chart");
		instantiateCheckAction();
	}
	
	public void instantiateCheckAction (){
		checking = new CheckAction (chars,input, score);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// TODO Auto-generated method stub 
		drawingComp.getBackGround().draw(0, 0, arg0.getWidth(), arg0.getHeight());		
		renderOval(arg2);
		
		if (checking != null && checking.getIconShowTime() != 0 && System.currentTimeMillis() - checking.getIconShowTime() > 300){
			checking.resetState();
		}
		
		if (!showMenue && player != null){
			renderImage();
			if (checking != null){
				checking.checkUserInput();
				renderGrade ();
			}		
			if (player.isEnd()){
				Record ();				
				showEndMenue(arg2);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			drawingComp.showPauseMenue();
		}
		stop = System.currentTimeMillis();
	}
	
	public void showEndMenue (Graphics arg2){
		drawingComp.showEndMenue();
		arg2.drawString("Total Score: " + score.getScore(), 120, 200);
		arg2.drawString("Greate: " + score.getGreat(), 120, 220);
		arg2.drawString("Good: " + score.getNice(), 120, 240);
		arg2.drawString("Bad: " + score.getBad(), 120, 260);
		arg2.drawString("Miss: " + score.getMiss(), 120, 280);
		arg2.drawString("Best Combo: " + score.getBestCombo(), 120, 300);
	}
	
	public void renderOval (Graphics arg2){
		arg2.setColor(org.newdawn.slick.Color.red);
		arg2.drawOval(120, 520, 60, 60);
		arg2.drawOval(220, 520, 60, 60);
		arg2.drawOval(320, 520, 60, 60);
		arg2.drawOval(420, 520, 60, 60);
	}
	
	public void renderImage (){
		for (Node node:chars){
			if (player.currentPosition() > node.getTargetBeat() * (60.f/song.getBPM())){
				double speed = (3.5);
				node.move(speed);
				drawingComp.getCircle().draw(node.getX(), node.getY());
			}
		}
	}

	public void renderGrade (){
		int x = container.getWidth()/2;
		int y = container.getHeight()/2;
		int imageWidth;
		int imageHight;
		if (checking.isGreat()){
			imageWidth = drawingComp.getGreat().getWidth() * (container.getWidth()/baseWidth);
			imageHight = drawingComp.getGreat().getHeight() * (container.getHeight()/baseHight);
			
			drawingComp.getGreat().draw(x, y, imageWidth, imageHight);			
		}else if (checking.isOk()){
			imageWidth = drawingComp.getOk().getWidth() * (container.getWidth()/baseWidth);
			imageHight = drawingComp.getOk().getHeight() * (container.getHeight()/baseHight);
					
			drawingComp.getOk().draw(x, y, imageWidth, imageHight);			
		}else if (checking.isBad()){
			imageWidth = drawingComp.getBad().getWidth()* (container.getWidth()/baseWidth);
			imageHight = drawingComp.getBad().getHeight() * (container.getHeight()/baseHight);
			
			drawingComp.getBad().draw(x, y, imageWidth, imageHight);									
		}else if (checking.isMiss()){
			imageWidth = drawingComp.getMiss().getWidth() * (container.getWidth()/baseWidth);
			imageHight = drawingComp.getMiss().getHeight() * (container.getHeight()/baseHight);
			
			drawingComp.getMiss().draw(x, y, imageWidth, imageHight);
		}
	}
	
		
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		if (player != null && player.isEnd() && !player.isStop()){
		}

		if (song != null && player == null){
			player = new MusicPlayer (song.getPath());
			player.Start();
			showMenue = false;
			score.reset();
		}
				
		if (checking != null){
			checking.checkNode();
			removeStopedCircle ();
			checking.getKey ();
		}
		
		if (input.isKeyDown(Input.KEY_ESCAPE)){
			player.stop();
			showMenue = true;
		}
		
		if (player != null && !player.isStop() && System.currentTimeMillis() - stop > 60){
			if (stop > 0){		
				player.stop();
			}
		}else if (player != null && player.isStop() && System.currentTimeMillis() - stop < 60 && !showMenue){
			player.resume();
		}
		
		if (drawingComp.getReset().isClicked(container)){
			reset ();
		}else if (drawingComp.getResume().isClicked(container)){
			resume();
		}else if (drawingComp.getQuit().isClicked(container)){
			quit ();
		}
		
		if (drawingComp.getReset_End().isClicked(container)){
			player.stop();
			reset ();
		}else if (drawingComp.getQuit_End().isClicked(container))		{
			quit();
		}
	}
	
	public void reset (){
		readCharFile(path);
		player.Start();
		instantiateCheckAction();
		score.reset();
		showMenue = false;					
	}
	
	public void resume (){
		player.resume();
		showMenue = false;					
	}
	
	public void quit (){
		player = null;
		checking = null;
		score.reset();
		gameContainer.enterState(GameState.Selection.getValue());
	}
	
	public void Record (){
		SaveFile saver = new SaveFile ();
		if (gameContainer.getUser()!= null){
			UserInform user = gameContainer.getUser();

			List<Challenge> challenges = user.gettrophyList();
			for (Challenge challenge:challenges){
				if (challenge.isComplete(score)){
					challenge.setComplete(true);
				}
			}
			
			HashMap<String, Integer> map= user.getScores();
			if (map.get(song.getSongName()) != null){
				int oldScore = map.get(song.getSongName());
				if (oldScore < score.getScore()){
					map.replace(song.getSongName(), score.getScore());
					saver.save(user);				
					gameContainer.setUpdateUser(user);
				}
			}else{
				user.addToMap(song.getSongName(), score.getScore());
				saver.save(user);
				song.setBestScore(score.getScore());
				gameContainer.setUpdateUser(user);				
			}
		}else{
			UserInform user = new UserInform ();

			List<Challenge> challenges = user.gettrophyList();
			for (Challenge challenge:challenges){
				if (challenge.isComplete(score)){
					challenge.setComplete(true);
				}
			}

			user.addToMap(song.getSongName(), score.getScore());
			saver.save(user);
			song.setBestScore(score.getScore());
			gameContainer.setUpdateUser(user);
		}
	}
	
	public void removeStopedCircle (){
		for (int i = 0; i < chars.size(); i++){
			if (chars.get(i).getY() <= 0.5)
				break;
			else if (chars.get(i).isStop()){
				chars.remove(i);
				i--;
			}
		}		
	}
	
	public void setContianer (Container container){
		this.gameContainer = container;
	}
			
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return GameState.Game.getValue();
	}
}