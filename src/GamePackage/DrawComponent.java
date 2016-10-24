package GamePackage;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DrawComponent {
	private Image ok, great, miss, bad, circle, backGround;
	private ImageButton resume, quit, reset, quit_end, reset_end;
	private Image menue, sakura;
	
	public DrawComponent (){
		readImages ();
	}
	
	private void readImages (){
		try {
			setBackGround("res/back.jpeg");
			setOk("res/ok.png");
			setGreat("res/great.png");
			setMiss("res/miss.png");
			setBad("res/bad.png");	
			setCircle("res/Circle.png");
			resume = new ImageButton ("graphics/resume.png");
			reset = new ImageButton ("graphics/restart.png");
			quit = new ImageButton ("graphics/quit.png");
			quit_end = new ImageButton ("graphics/quit.png", 310, 500);
			reset_end = new ImageButton ("graphics/restart.png", 120, 500);
			resume.setX(300 - resume.getWidth()/2);
			reset.setX(300 - reset.getWidth()/2);
			quit.setX(300 - quit.getWidth()/2);
			resume.setY(200);
			reset.setY(280);
			quit.setY(360);
			menue = new Image ("graphics/menu.png");
			sakura = new Image ("graphics/SAKURA.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	

	public void showPauseMenue (){
		menue.draw(300 - menue.getWidth()/2,350 - menue.getHeight()/2);
		sakura.draw(400, 500);
		resume.draw();
		reset.draw();
		quit.draw();
	}
	
	public void showEndMenue (){
		menue.draw(300 - menue.getWidth()/2,350 - menue.getHeight()/2);		
		quit_end.draw();
		reset_end.draw();
	}
	
	public Image getOk() {
		return ok;
	}

	public void setOk(String path) throws SlickException {
		ok = new Image (path);
	}

	public Image getGreat() {
		return great;
	}

	public void setGreat(String path) throws SlickException {
		great = new Image (path);
	}

	public Image getMiss() {
		return miss;
	}
	
	public void setMiss(String path) throws SlickException {
		miss = new Image (path);
	}

	public Image getBad() {
		return bad;
	}

	public void setBad(String path) throws SlickException {
		bad = new Image (path);
	}

	public Image getCircle() {
		return circle;
	}

	public void setCircle(String path) throws SlickException {
		circle = new Image (path);
	}

	public Image getBackGround() {
		return backGround;
	}

	public void setBackGround(String path) throws SlickException {
		backGround = new Image (path);
	}
	
	public ImageButton getResume (){
		return resume;
	}
	
	public ImageButton getReset (){
		return reset;
	}
	
	public ImageButton getReset_End (){
		return reset_end;
	}
	
	public ImageButton getQuit (){
		return quit;
	}
	
	public ImageButton getQuit_End (){
		return quit_end;
	}
}
