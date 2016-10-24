package GamePackage;

import java.io.*;  
import java.net.URL;  
import javax.sound.sampled.*;  
import javax.swing.*;  

public class MusicPlayer {
	private boolean isStop = true;
	private double startPosition;
	private double currentPosiition;
	private String songPath;
    AudioInputStream audioIn;  
    Clip clip;  

    public MusicPlayer (String songPath){
		this.songPath = songPath;
	}
		
	public boolean isStop (){
		return isStop;
	}
	
	public void setPath (String songPath){
		this.songPath = songPath;
	}
	
	public boolean openFile (){
		try {
			File file = new File(songPath);
            audioIn = AudioSystem.getAudioInputStream(file);  
            return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			return false;
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			return false;
		}
	}
	
	public void Start() {
		if (isStop() && openFile()){
            try {
				clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
                this.isStop = false;
				startPosition = (double) (System.currentTimeMillis()*1.00/1000.f);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
	}
	
	public void Start_Loop (){
		if (isStop() && openFile()){
            try {
				clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
                this.isStop = false;
				startPosition = (double) (System.currentTimeMillis()*1.00/1000.f);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
	}

	public void stop() {
		clip.stop();
		currentPosiition = currentPosition();
		isStop = true;
	}
	
	public boolean isEnd (){
		return !clip.isRunning();
	}
		
	public double currentPosition (){
		return (double) (System.currentTimeMillis()*1.00/1000.f) - startPosition;
	}
	
	public void resume (){
		clip.start();
		startPosition = (double) (System.currentTimeMillis()*1.00/1000.f) - currentPosiition;
		isStop = false;
	}
}
