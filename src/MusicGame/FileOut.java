package MusicGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class FileOut {
	private File file;
	
	private OutputStream os;
	private OutputStreamWriter osWriter;
	private BufferedWriter writer;
	
	public FileOut() {
	}

	public FileOut(String filePath) {
		file = new File(filePath);
		if (file != null && file.isFile() && file.canRead()) {
			try {
				os=new FileOutputStream(file);
				osWriter=new OutputStreamWriter(os);
				writer=new BufferedWriter(osWriter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean scoreFileout(Score score) throws IOException{
		
		if(score!=null&&writer!=null){
			String txtLine=null;
			if((txtLine=String.valueOf(score.getGreat()))!=null){
				writer.write(txtLine);
				writer.newLine();								
			}
			if((txtLine=String.valueOf(score.getNice()))!=null){
				writer.write(txtLine);
				writer.newLine();								
			}
			if((txtLine=String.valueOf(score.getBad()))!=null){
				writer.write(txtLine);
				writer.newLine();								
			}
			if((txtLine=String.valueOf(score.getMiss()))!=null){
				writer.write(txtLine);
				writer.newLine();								
			}							
			
			if((txtLine=String.valueOf(score.getScore()))!=null){
				writer.write(txtLine);
				writer.newLine();								
			}
			os.flush();
			osWriter.flush();
			writer.flush();
			os.close();
			osWriter.close();
			writer.close();
			return true;			
			
		}
		return false;
		
	}

}
