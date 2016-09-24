import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;


public class HomeScreen extends JPanel implements ChangeListener{
	private JPanel menu,volumePanel;
	private JButton startGame, multiPlay, trophy, gameHistory, exit;
	private JSlider volume;
	private JLabel volumeLabel;
	
	public HomeScreen(){
		super(new BorderLayout());
		
		
		//Initialize components
		menu = new JPanel(new GridBagLayout());
		volumePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		GridBagConstraints cons = new GridBagConstraints();
		startGame=new JButton("Single-Play");
		getStartGame().setPreferredSize(new Dimension(150, 30));
		multiPlay=new JButton("Multi-Play");
		getMultiPlay().setPreferredSize(new Dimension(150, 30));
		trophy=new JButton("Trophy");
		getTrophy().setPreferredSize(new Dimension(150, 30));
		gameHistory = new JButton("Game History");
		gameHistory.setPreferredSize(new Dimension(150, 30));
		exit = new JButton("Exit");
		exit.setPreferredSize(new Dimension(150, 30));
		volumeLabel = new JLabel("Vol.");
		volume = new JSlider(JSlider.HORIZONTAL,0,100,30);
		volume.setMinorTickSpacing(10);
		volume.setMajorTickSpacing(50);
		volume.setPaintTicks(true);
		volume.setPreferredSize(new Dimension(200,40));
		
		
		
		//Distribute components
		cons.insets = new Insets(0,10,10,10);
		cons.gridx=0;
		cons.gridy=1;
		menu.add(getStartGame(), cons);
		cons.gridx=0;
		cons.gridy=2;
		menu.add(getMultiPlay(), cons);
		cons.gridx=0;
		cons.gridy=3;
		menu.add(getTrophy(), cons);
		cons.gridx=0;
		cons.gridy=4;
		menu.add(gameHistory, cons);
		cons.gridx=0;
		cons.gridy=5;
		menu.add(exit, cons);
		
		
		this.add(menu, BorderLayout.WEST);
		volumePanel.add(volumeLabel);
		volumePanel.add(volume);
		this.add(volumePanel,BorderLayout.SOUTH);
		
		
		
		//Add slider change listener
		volume.addChangeListener(this);
		
		
	}
	
	//Slider changer( Volume change)
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==volume){
			setOutputVolume(volume.getValue()/10);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}


		public JButton getTrophy() {
			return trophy;
		}

		public JButton getStartGame() {
			return startGame;
		}

		public JButton getMultiPlay() {
			return multiPlay;
		}

		public JButton getGameHistory() {
			return gameHistory;
		}

		public JButton getExit() {
			return exit;
		}
		
		public static void setOutputVolume(float value)
		{
		    String command = "set volume " + value;
		    try
		    {
		        //For mac OS
			/*
			ProcessBuilder pb = new ProcessBuilder("osascript","-e",command);
		        pb.directory(new File("/usr/bin”));
			*/

			//For windows OS
			ProcessBuilder pb = new ProcessBuilder(“CMD”,”/C”,command);
		        Process p = pb.start();
		        p.waitFor();
		        BufferedReader reader =
		                new BufferedReader(new InputStreamReader(p.getInputStream()));
		    }
		    catch(Exception e)
		    {
		        System.out.println(e);
		    }
		}
}
