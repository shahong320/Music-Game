package GamePackage;



import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Song {
	private String songName;
	private String artistName;
	private String path;
	private int bpm;
	private Image songCover;
	private int BestScore;
	
	public Song (){
		
	}
	
	public Song (String path){
		this.path = path;
	}

	public Song (String path, int bpm){
		this.path = path;
		this.bpm = bpm;
	}
	
	public Song (String path, int bpm, 	String songName, String artistName) throws SlickException{
		this.path = path+".wav";
		this.bpm = bpm;
		songCover= new Image(path+".jpg");
		this.setSongName(songName);
		this.setArtistName(artistName);
		
	}
	
	public void setBestScore (int score){
		this.BestScore = score;
	}
	
	public int getScore (){
		return this.BestScore;
	}
	
	public void setBPM (int bpm){
		this.bpm = bpm;
	}
	
	public int getBPM (){
		return bpm;
	}

	public void setPath (String path){
		this.path = path + ".wav";
	}
	
	public String getPath (){
		return path;
	}
	
	public void setSong (String path, int bpm, 	String songName, String artistName){
		this.path = path;
		this.bpm = bpm;
		
		this.setSongName(songName);
		this.setArtistName(artistName);
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}	
	
	public Image getSongCover(){
		
		return songCover;
	}
	
	public String toString (){
		String aString = "";
		aString += "Title: " + this.songName +"\n";
		aString += "\nArtist: " + this.artistName + "\n";
		return aString;
	}
}
