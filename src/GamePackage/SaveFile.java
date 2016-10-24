package GamePackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveFile {
	
	public boolean save (UserInform user){
		try {
			System.out.println("Saving");
			File afile = new File ("MusicGame.save");
			if (!afile.exists()){
				afile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(afile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(user);
			oos.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
