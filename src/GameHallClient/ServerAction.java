package GameHallClient;

import java.net.Socket;

public interface ServerAction {
	public void execute(Request request, Response response, Socket socket);
}
