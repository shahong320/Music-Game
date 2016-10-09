import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class GameHistory extends JPanel{
	private JList<?> playerList;
	private JButton back;
	private JPanel top, bottom;
	private ArrayList<String> history;
	
	public ArrayList<String> getHistory() {
		return history;
	}
	
	public GameHistory(){
		super(new GridBagLayout());
		Dimension screen = this.getToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(screen.width/2,screen.height/2));
		
		//initialize
		history = new ArrayList<String>();
		inputRecords();
		GridBagConstraints cons = new GridBagConstraints();
		top = new JPanel();
		bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		playerList = new JList(history.toArray());
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		back = new JButton("Back Menu");
		JScrollPane players=new JScrollPane(playerList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		players.setPreferredSize(new Dimension(screen.width/2*3/4,screen.height/2*3/5));
		
		//Adding component
		top.add(players);
		bottom.add(back);
		cons.gridx=0;
		cons.gridy=0;
		this.add(top, cons);
		cons.gridx=0;
		cons.gridy=1;
		this.add(bottom, cons);
		
	}


	public JButton getBack() {
		return back;
	}
	
	
	
	public void addRecord(String name, int score) {
		history.add(name+"|"+score);
	}
	
	public boolean searchRecord(String name){
		boolean result=false;
		for(String str:history){
			if(str.contains(name))
				result=true;
		}
		return result;
	}
	
	public void inputRecords()
	{
		BufferedReader in;
		try {
			String str;
			in = new BufferedReader(new FileReader("src/TXT_FILES/GameHistory.txt"));
			while((str = in.readLine()) != null){
				history.add(str);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void outputRecords(){
		try {
			FileWriter out=new FileWriter(new File("src/GameHistory.txt"));
			for(String str:history){
				out.write(str);
				out.write("\r\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
