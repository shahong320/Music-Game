package GamePackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class is found at https://github.com/gwaerondor/Rhythm
 * 
 */
public class ChartFileParser {	
	private static String parse(String filePath) {
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			return stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static ArrayList<Node> getChartFromFile(String filePath) {
		ArrayList<Node> chart = new ArrayList<Node>();
		String textChart = parse(filePath);
		String[] lines = textChart.split("\n");
		for(String line : lines){
			String[] parts = line.split(":");
			String laneString = parts[1];
			String beatString = parts[0];
			String[] multipleLanes = laneString.split(",");
			float beat = Float.parseFloat(beatString);
			for(String l : multipleLanes) {
				int lane = Integer.parseInt(l);
				chart.add(new Node(lane, beat));				
			}
		}
		return chart;
	}
	
}