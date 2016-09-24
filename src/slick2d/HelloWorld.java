package slick2d;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HelloWorld extends BasicGame{
    public HelloWorld(String title) throws SlickException {
        super(title);
       
    }
    public void init(GameContainer container) throws SlickException {

    }
    public void update(GameContainer container, int delta) 
        throws SlickException {

    }
    public void render(GameContainer container, Graphics graphics) 
        throws SlickException {
            graphics.drawString("Hello, World!", 400, 100);
    }
    

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new HelloWorld("Hello World"));
        app.setDisplayMode(800, 600, false);
        app.start();
    }
}
