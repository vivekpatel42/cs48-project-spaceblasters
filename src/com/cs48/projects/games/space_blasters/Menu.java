//package com.cs48.projects.games.space_blasters;
import java.awt.*;
import java.awt.event.*;
import sun.audio.*;
import javax.swing.*;
import java.io.*;

public class Menu extends JPanel {

	private JButton startGame;	
	private JButton highScores;
	private JButton instructions;
	private JButton options;
        private JButton sound;
	
        public Menu() {
		super();
		this.setSize(600, 400);
		this.setLayout(new FlowLayout());
		startGame = new JButton("Start Game");
		highScores = new JButton("High Scores");
		instructions = new JButton("Instructions");
		options = new JButton("Options");
		sound = new JButton("Music");
		this.add(startGame);
		this.add(highScores);
		this.add(instructions);
		this.add(options);
		this.add(sound);
		sound.addActionListener(new AL());

		
	}
    public static class AL implements ActionListener{
	       public final void actionPerformed(ActionEvent e){
		   music();
	       }
	}
    
    
        public static void music() 
       {        
	       AudioPlayer MGP = AudioPlayer.player;
               AudioStream BGM;
               AudioData MD;
	       ContinuousAudioDataStream loop = null;
	       try{ 
	       InputStream soundTest = new FileInputStream("MusicTest.wav"); 
	       BGM = new AudioStream(soundTest);
	       AudioPlayer.player.start(BGM);
	       }
	       catch(IOException error){}
	       MGP.start(loop);
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
