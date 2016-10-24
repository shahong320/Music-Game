package GamePackage;


import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Panel.MainPanel;
import UIFrame.MainFrame;

public class Selection extends BasicGameState{
	
	private Image background ;
	private Image logo;
	Image backgroundXiaoniao;
	private ArrayList<Song> songList;
	private MusicPlayer musicplayer;
	private Container container;
	private TrueTypeFont font;
	int listIndex;
	String songdetail;
	
	Image musicIcon;
	Image backIcon;
	Image arrowRight;
	Image arrowLeft;
	
	ImageButton exitButton;
	ImageButton rightButton;
	ImageButton leftButton;
	private ImageButton start;



	
	public Selection(int state) {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		listIndex=0;
		songList=new SongsList().getSongList();
		background= new Image("/graphics/background1.jpg");
		logo=new Image("/graphics/logo.jpg");
		backgroundXiaoniao=new Image("/graphics/backxiaoniao.png");
		
		musicIcon=songList.get(listIndex).getSongCover();
		
		exitButton=new ImageButton("/graphics/Exit.png", 450, 560, 140,62);
		leftButton=new ImageButton("/graphics/ArrowLeft2.png", 210, 230, 40, 40);
		rightButton=new ImageButton("/graphics/ArrowRight2.png", 430, 230, 40, 40);	
		start = new ImageButton ("graphics//Start.png", 450, 480, 140, 62);
		
		java.awt.Font awtFont = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 24);
	    font = new TrueTypeFont(awtFont, false);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0,0,600,700);
		logo.draw(230,20,320,100);
		backgroundXiaoniao.draw(0,455,200,195);
		
		musicIcon.draw(260,170, 160, 160);
		font.drawString(250, 350, "Title: " + songList.get(listIndex).getSongName(), Color.blue);		
		font.drawString(250, 380, "Artist: " + songList.get(listIndex).getArtistName(), Color.blue);		
		int score;
		if (container.getUser() != null){
			HashMap<String, Integer> map = container.getUser().getScores();
			if (map.get(songList.get(listIndex).getSongName()) == null){
				score = 0;				
			}else{
				score = map.get(songList.get(listIndex).getSongName());
			}
		}else{
			score = 0;
		}
		
		font.drawString(250, 410, "Best Score : " + score, Color.blue);
		
		exitButton.draw();
		leftButton.draw();
		rightButton.draw();
		start.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (musicplayer == null){
			musicplayer = new MusicPlayer (songList.get(listIndex).getPath());
			startMusic (songList.get(listIndex).getPath());			
		}
		
		if(leftButton.isClicked(gc)){
			if(listIndex>0){
				listIndex--;
				songdetail=songList.get(listIndex).getSongName()+"-"+songList.get(listIndex).getArtistName();
				musicIcon=songList.get(listIndex).getSongCover();
				stopMusic ();
				startMusic (songList.get(listIndex).getPath());
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(rightButton.isClicked(gc)){
			if(listIndex<songList.size()-1){
				listIndex++;
				songdetail=songList.get(listIndex).getSongName()+"-"+songList.get(listIndex).getArtistName();
				musicIcon=songList.get(listIndex).getSongCover();
				stopMusic ();
				startMusic (songList.get(listIndex).getPath());
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		if(exitButton.isClicked(gc)){
			stopMusic ();
			musicplayer = null;
			container.getPanel().getFrame().exit();
		}
		
		if (start.isClicked(gc)){
			stopMusic ();
			musicplayer = null;
			container.getGame().setSong(songList.get(listIndex));
			container.enterState(GameState.Game.getValue());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void startMusic (String path){
		musicplayer.setPath(path);
		musicplayer.Start_Loop();;
	}
	
	public void setContianer (Container container){
		this.container = container;
	}
	
	public void stopMusic (){
		musicplayer.stop();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return GameState.Selection.getValue();
	}

}
