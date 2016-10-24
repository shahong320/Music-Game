package Panel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Challenge.Challenge;
import GamePackage.ReadSave;
import GamePackage.UserInform;

public class TrophyPanel extends JPanel{
//	private JList<?> trophyList;
	private JTextArea DescriptionField;
	private JButton back;
	JScrollPane scroll;
	
	public JButton getBack() {
		return back;
	}

	private JLabel playerName;
	
	public TrophyPanel(){
		super(new BorderLayout());
		this.setSize(1000, 700);
		this.setBorder(new TitledBorder(new EtchedBorder(), "Trophy list"));
		
		//initialize the components
		DescriptionField = new JTextArea(34,75);
		scroll = new JScrollPane(DescriptionField);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		back = new JButton("Back Menu");
		
		List<Challenge> trophys;
		UserInform auser = new ReadSave ().readSave();
		System.out.println(auser);

		if (auser == null){
			auser = new UserInform ();
		}
		
		trophys = auser.gettrophyList();
		String aString = "";
		
		for (Challenge trophy:trophys){
			aString += "Trophy Description: " + trophy.getDescription() + "\n";
			aString += "Difficulty: " + trophy.getDifficulty() + "\n";
			aString += "Complete State: ";
			if (trophy.isComplete()){
				aString += "Complete\n\n";
			}else{
				aString += "Incomplete\n\n";				
			}			
		}

		DescriptionField.setText(aString);
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel playerPanel = new JPanel(new BorderLayout());
		
		top.add(playerPanel);
		bottom.add(scroll);
		bottom.add(back);
		this.add(top, BorderLayout.WEST);
		this.add(bottom, BorderLayout.AFTER_LAST_LINE);
		
	}
	

	
}
