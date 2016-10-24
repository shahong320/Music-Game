package GameHallClient;
import java.io.PrintStream;
import java.net.Socket;

public class User {
		//User's unique ID
		private String id;	
		//User Name
		private String name;
		
		//0=Male, 1=Female
		private int sex;
		
		//User's Socket
		private Socket socket;
		//there should have user score, it s should be add later when we finished the game program.
		private int score;
		
		public int getScore(){
			return this.score;
		}
		public void setScore(int score){
			this.score= score;
		}
			
		public User() {
			
		}
		
		public User(String id, String name, int sex) {
			this.id = id;
			this.name = name;
			this.sex = sex;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public Socket getSocket() {
			return socket;
		}

		public void setSocket(Socket socket) {
			this.socket = socket;
		}
		
		public PrintStream getPrintStream() {
			try {
				PrintStream ps = new PrintStream(this.socket.getOutputStream());
				return ps;
			} catch (Exception e) {
				return null;
			}
		}
}
