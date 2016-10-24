import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Trophy extends JPanel{
	private JList<?> trophyList, playerList;
	private JTextArea DescriptionField;
	private JButton back;
	public JButton getBack() {
		return back;
	}

	private JLabel playerName;
	
	public Trophy(){
		super(new BorderLayout());
		Dimension screen = this.getToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(screen.width/2,screen.height/2));
		
		//initialize the components
		trophyList = new JList();
		trophyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerList = new JList();
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DescriptionField = new JTextArea(4,3);
		DescriptionField.setPreferredSize(new Dimension(screen.width/2/3*2,screen.height/2/3));
		back = new JButton("Back Menu");
		playerName = new JLabel("Player Name");
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel playerPanel = new JPanel(new BorderLayout());
		JScrollPane trophies=new JScrollPane(trophyList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		trophies.setPreferredSize(new Dimension(screen.width/2/3,screen.height/2/3*2));
		JScrollPane players=new JScrollPane(playerList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		players.setPreferredSize(new Dimension(screen.width/2/2,screen.height/2/3*2));
		
		//Layout
		
		top.add(trophies);
		playerPanel.add(playerName, BorderLayout.NORTH);
		playerPanel.add(players, BorderLayout.CENTER);
		top.add(playerPanel);
		bottom.add(DescriptionField);
		bottom.add(back);
		this.add(top, BorderLayout.WEST);
		this.add(bottom, BorderLayout.AFTER_LAST_LINE);
		
		//List listener
		trophyList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		
	}
	

	
}
