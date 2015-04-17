//package com.cs48.projects.games.space_blasters;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

	private JButton startGame;	
	private JButton highScores;
	private JButton instructions;
	private JButton options;

	public Menu() {
		super();
		this.setSize(600, 400);
		this.setLayout(new FlowLayout());
		startGame = new JButton("Start Game");
		highScores = new JButton("High Scores");
		instructions = new JButton("Instructions");
		options = new JButton("Options");
		this.add(startGame);
		this.add(highScores);
		this.add(instructions);
		this.add(options);
	}

	/*public Menu(int width, int height) {
		super();
		this.setSize(width, height);
		this.setLayout(new FlowLayout());
	} */

	public static void main(String[] args) {
		JFrame mainWindow = new JFrame("Space Blasters");
		Menu menu = new Menu();
		mainWindow.add(menu);
		mainWindow.setVisible(true);
	}
}
