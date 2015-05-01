package cs48.project.game.Space_Blasters;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Vivek Patel
 */

public class Menu extends JPanel {

	protected JButton startGame;	
	protected JButton highScores;
	protected JButton instructions;
	protected JButton options;
     //protected JButton sound;

	public JButton getStartGame() {
		 return startGame;
	 }
	public JButton getHighScores() {
		return highScores;
	}
	public JButton getInstructions() {
		return instructions;
	}
	public JButton getOptions() {
		return options;
	}

	public Menu() {

		this.setSize(800, 600);
		this.setBackground(Color.BLACK);
		startGame = new JButton("Start Game");
		//ImageIcon img = new ImageIcon("~/res/startGameNoSelect.png");
		//startGame.setIcon(img);
		highScores = new JButton("High Scores");
		instructions = new JButton("Instructions");
		options = new JButton("Options");
		//sound = new JButton("Music");
		this.add(startGame, BorderLayout.CENTER);
		this.add(highScores, BorderLayout.CENTER);
		this.add(instructions, BorderLayout.CENTER);
		this.add(options, BorderLayout.CENTER);
		//this.add(sound, BorderLayout.CENTER);
		//sound.addActionListener(new AL());
	}}













