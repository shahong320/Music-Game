package GamePackage;


import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class SongsList {
	
	private ArrayList<Song> songList;

	public SongsList() throws SlickException {
		songList = new ArrayList<Song>();
		loadSongs();
	}
	
	private void loadSongs() throws SlickException {
		
		songList.add(new Song("AudioFile/StartDash",150,"StartDash","Love Live!"));
		songList.add(new Song("AudioFile/HelloWorld", 106, "HelloWorld", "BUMP of CHIKEN"));
		songList.add(new Song("AudioFile/ELECT", 106, "ELECT", "Miku"));
	}
	
	public ArrayList<Song> getSongList(){
		
		return songList;
		
	}

}
