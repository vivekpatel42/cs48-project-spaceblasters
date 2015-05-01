package cs48.project.game.Space_Blasters;

import javax.swing.*;
import java.awt.*;

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
	}

	/*public static class AL implements ActionListener{
	       public final void actionPerformed(ActionEvent e){
		   music();
	       }
	}
    
    
        public static void music() {     
		String musicTest = "MusicTest.wav";
		Media hit = new Media(musicTest);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();	   
	       /*AudioPlayer MGP = AudioPlayer.player;
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

	}*/


	public static void main(String[] args) {
		JFrame mainWindow = new JFrame("Space Blasters");
		Menu menu = new Menu();
		mainWindow.getContentPane().add(menu);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
