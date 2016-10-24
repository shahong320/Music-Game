package Panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
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

import javax.imageio.ImageIO;
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

/**
 * This class is the UI of homescreen. It has 5 buttons and 1 slider. The slider
 * is used to change the system volume with the method in this class.
 */

public class HomePagePanel extends JPanel implements ChangeListener {
	private JPanel menu, volumePanel, background;
	private JButton singleGame, multiPlay, trophy, gameHistory, exit;
	private JSlider volume;
	private JLabel volumeLabel;

	/**
	 * Initialize this class and its variables, and component distribution
	 */
	public HomePagePanel() {
		super();

		// Initialize components
		menu = new JPanel(new GridBagLayout());
		menu.setOpaque(false);
		volumePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		volumePanel.setOpaque(false);
		GridBagConstraints cons = new GridBagConstraints();
		singleGame = new JButton(new ImageIcon("graphics/SinglePlay.png"));
		getSingleGame().setPreferredSize(new Dimension(162, 58));
		singleGame.setBorderPainted(false);
		singleGame.setContentAreaFilled(false);
		multiPlay = new JButton(new ImageIcon("graphics/Multiplay.png"));
		multiPlay.setIcon(new ImageIcon("graphics/Multiplay.png"));
		getMultiPlay().setPreferredSize(new Dimension(162, 58));
		multiPlay.setBorderPainted(false);
		multiPlay.setContentAreaFilled(false);
		trophy = new JButton(new ImageIcon("graphics/Trophy.png"));
		getTrophy().setPreferredSize(new Dimension(162, 58));
		trophy.setBorderPainted(false);
		trophy.setContentAreaFilled(false);
		exit = new JButton(new ImageIcon("graphics/HomeExit.png"));
		exit.setPreferredSize(new Dimension(162, 58));
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		volumeLabel = new JLabel("Vol.");
		volume = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
		volume.setMinorTickSpacing(10);
		volume.setMajorTickSpacing(50);
		volume.setPaintTicks(true);
		volume.setPreferredSize(new Dimension(200, 40));
		volume.setOpaque(false);
		background = new JPanel(new BorderLayout());
		background.setOpaque(false);
		background.setPreferredSize(new Dimension(1000, 650));

		// Distribute components
		cons.insets = new Insets(0, 10, 10, 10);
		cons.gridx = 0;
		cons.gridy = 1;
		menu.add(getSingleGame(), cons);
		cons.gridx = 0;
		cons.gridy = 2;
		menu.add(getMultiPlay(), cons);
		cons.gridx = 0;
		cons.gridy = 3;
		menu.add(getTrophy(), cons);
		cons.gridx = 0;
		cons.gridy = 4;
		menu.add(exit, cons);

		background.add(menu, BorderLayout.WEST);
		volumePanel.add(volumeLabel);
		volumePanel.add(volume);
		background.add(volumePanel, BorderLayout.SOUTH);
		this.add(background);

		// Add slider change listener
		volume.addChangeListener(this);

	}

	/**
	 * Set Background
	 */
	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("graphics/HomeScreen.png");
		Image img = icon.getImage();
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	/**
	 * Slider changer( Volume change)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == volume) {
			setOutputVolume(volume.getValue() / 10);
		}

	}

	/**
	 * @return trophy button value
	 */
	public JButton getTrophy() {
		return trophy;
	}

	/**
	 * @return singleGame button value
	 */
	public JButton getSingleGame() {
		return singleGame;
	}

	/**
	 * @return multiPlay button value
	 */
	public JButton getMultiPlay() {
		return multiPlay;
	}

	/**
	 * @return gameHistory button value
	 */
	public JButton getGameHistory() {
		return gameHistory;
	}

	/**
	 * @return exit button value
	 */
	public JButton getExit() {
		return exit;
	}

	/**
	 * Method for changing system volume
	 */
	public static void setOutputVolume(float value) {
		String command = "set volume " + value;
		try {
			// For mac OS

			// ProcessBuilder pb = new ProcessBuilder("osascript", "-e",
			// command);
			// pb.directory(new File("/usr/bin"));
			// Process p = pb.start();
			// p.waitFor();

			
			
			// For windows OS
			Mixer.Info[] mixers = AudioSystem.getMixerInfo();
			for (Mixer.Info mixerInfo : mixers) {
				// System.out.println("mixer name: " + mixerInfo.getName());
				Mixer mixer = AudioSystem.getMixer(mixerInfo);
				Line.Info[] lineInfos = mixer.getTargetLineInfo(); // target,
																	// not
																	// source
				// changes all the volumes

				for (Line.Info lineInfo : lineInfos) {
					// System.out.println(" Line.Info: " + lineInfo);
					Line line = null;
					boolean opened = true;
					try {
						line = mixer.getLine(lineInfo);
						opened = line.isOpen() || line instanceof Clip;
						if (!opened) {
							line.open();
						}
						FloatControl volCtrl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
						// System.out.println(volCtrl.getMinimum());
						volCtrl.setValue((float) value/10);
						// System.out.println(" volCtrl.getValue() = " +
						// volCtrl.getValue());
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException iaEx) {
						// System.out.println(" -!- " + iaEx);
					} finally {
						if (line != null && !opened) {
							line.close();
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
