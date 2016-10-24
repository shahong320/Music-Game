package GameHallServer;

/**
 * 
 * @author Hongzhousha
 * Main 
 *
 */
public class MainOfServer extends Thread{
	
	public void run(){
		new Server();
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
