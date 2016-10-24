package GamePackage;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageButton {
	private Image buttonImage;
	private int height;
	private int width;
	private float x;
	private float y;
	
	public ImageButton (String path){
		try {
			buttonImage = new Image (path);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.height = buttonImage.getHeight();
		this.width = buttonImage.getWidth();
	}
	
	public ImageButton (String path, float x, float y){
		try {
			buttonImage = new Image (path);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		this.height = buttonImage.getHeight();
		this.width = buttonImage.getWidth();
	}
	
	public ImageButton (String path, float x, float y, int width, int height){
		try {
			buttonImage = new Image (path);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setX (float x){
		this.x = x;
	}
	
	public void setY (float y){
		this.y = y;
	}
	
	public float getX (){
		return x;
	}
	
	public float getY (){
		return y;
	}
	
	public int getHeight (){
		return height;
	}
	
	public int getWidth (){
		return width;
	}
	
	public void draw (){
		buttonImage.draw(getX(), getY(), width, height);
	}
	
	public void draw(int x,int y){		
		buttonImage.draw(x, y);
	}
	
	public boolean isClicked (GameContainer container){
		if ((Mouse.getX() > x && Mouse.getX() < x + width) && (Mouse.getY() < container.getHeight() - y
				&& Mouse.getY() > container.getHeight() - y - height)) {
			if (Mouse.isButtonDown(0)) {
				return true;
			}
		}
		
		return false;
	}
}
