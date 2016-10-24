package GamePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadSave {
	public UserInform readSave (){
		FileInputStream fis;
		try {
			fis = new FileInputStream("MusicGame.save");
			ObjectInputStream ois = new ObjectInputStream(fis);
			UserInform user = (UserInform) ois.readObject();
			ois.close();
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
