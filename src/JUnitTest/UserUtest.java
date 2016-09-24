package JUnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import GameHallClient.User;

public class UserUtest {
    private User user;
     @Before
     public void setUp() {
        user = new User("S1","name",1);
     }

    @Test
    public void testID(){      
         String ID = "S1";
         String ID1 = user.getId();
         assertEquals(ID, ID1);
     }
    
    @Test
    public void testName(){      
         String name = "name";
         String name1 = user.getName();
         assertEquals(name, name1);
     }
    
    @Test
    public void testSex(){      
         int sex = 1;
         int sex1 = user.getSex();
         assertEquals(sex, sex1);
     }
}
