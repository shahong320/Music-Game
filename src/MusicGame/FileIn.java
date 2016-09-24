package MusicGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileIn {
	private File file;
	private BufferedReader reader;
	private InputStream is;
	private InputStreamReader isReader;

	public FileIn() {
	}

	public FileIn(String filePath) {
		file = new File(filePath);
		if (file != null && file.isFile() && file.canRead()) {
			try {
				is = new FileInputStream(file);
				isReader = new InputStreamReader(is);
				reader = new BufferedReader(isReader);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Score> readHistroy() throws IOException{
		ArrayList<Score> history=null;
		String txtLine=null;
		if(reader!=null){
			history=new ArrayList<Score>();
			while((txtLine=reader.readLine())!=null){
				Score score=new Score();
				score.setGreat(Integer.parseInt(txtLine));
				if ((txtLine = reader.readLine()) != null) {
					score.setNice(Integer.parseInt(txtLine));
				}
				if ((txtLine = reader.readLine()) != null) {
					score.setBad(Integer.parseInt(txtLine));
				}
				if ((txtLine = reader.readLine()) != null) {
					score.setMiss(Integer.parseInt(txtLine));
				}
				if ((txtLine = reader.readLine()) != null) {
					score.setCombo(Integer.parseInt(txtLine));
				}
				if ((txtLine = reader.readLine()) != null) {
					score.setScore(Integer.parseInt(txtLine));
				}
				history.add(score);
			}
			
		}	
		return history;
	}
	public static void main(String[] args) throws IOException{
		ArrayList<Score> a=new FileIn("test.saved.chart").readHistroy();
		System.out.println(a.get(0));
		
		
	}
}
