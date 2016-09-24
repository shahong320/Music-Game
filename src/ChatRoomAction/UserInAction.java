package ChatRoomAction;

import java.net.Socket;

import ChatRoomClient.ChatContext;
import GameHallClient.Request;
import GameHallClient.Response;
import GameHallClient.ServerAction;
import GameHallClient.User;
import GameHallClient.XStreamUtil;
// �û����������ҵķ�����������
public class UserInAction implements ServerAction{

	@Override
	public void execute(Request request, Response response, Socket socket) {
		// TODO Auto-generated method stub
		//�õ��½��������ҵ��û�
		User user = (User)request.getParameter("user");
		//�����û���Socet
		user.setSocket(socket);
		//�ŵ���������
		ChatContext.users.put(user.getId(), user);
		//����ԭ�����û�, ����������, ���������е��û���Ϣ
		response.setData("users", ChatContext.users);
		user.getPrintStream().println(XStreamUtil.toXML(response));
		//����ȫ���û�, �����û�����
		String receiveUserInAction = (String)request.getParameter("userInAction");
		//�ÿͻ��˴��������˽��������ҵ�Action
		response.setActionClass(receiveUserInAction);
		response.setData("newUser", user);
		//�������û�����
		for (String key : ChatContext.users.keySet()) {
			User u = ChatContext.users.get(key);
			if (u.getId() != user.getId()) {
				u.getPrintStream().println(XStreamUtil.toXML(response));
			}
		}
	}

}
