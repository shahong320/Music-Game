package JUnitTest;

import static org.junit.Assert.*;

import java.net.Socket;

import java.util.HashMap;

import java.util.Map;

import org.junit.Before;

import org.junit.Test;

import ChatRoomClient.ChatContext;

import GameHallClient.XStreamUtil;

import GameHallServer.ServerThread;

public class ChatRoomUtest {
    private  ChatContext CC;
    private ServerThread ST;
    private Socket socket;
    private XStreamUtil XU;
    
     @Before
     public void setUp() {
       CC = new ChatContext();
       ST = new ServerThread(socket);
     }

    @Test
    public void testContext(){      
         Map context = new HashMap();       
         assertEquals(context, CC.users);
     }
    
    @Test
    public void testActionStore(){      
    	 Map action = new HashMap();        
         assertEquals(action, ST.actions);
     }
    
    @Test
    public void testXStreamUtil(){  
    	Object o3 = "ss";
    	Object o2 = "ss";
    	String o1 = XU.toXML(o2);
        assertEquals(o1, XU.toXML(o3));
     }
}

