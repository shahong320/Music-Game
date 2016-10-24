package GameHallServer;

import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	ServerSocket serverSocket;
	
	public Server() {
		try {
			this.serverSocket = new ServerSocket(12000);
			while(true) {
				Socket socket = this.serverSocket.accept();
				new ServerThread(socket).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException("Server Error: " + e.getMessage());
		}
	}
	
}
