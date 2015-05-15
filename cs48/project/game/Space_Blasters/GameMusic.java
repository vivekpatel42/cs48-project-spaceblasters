package cs48.project.game.Space_Blasters;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Richard Alvarez on 5/13/2015.
 */
public class GameMusic implements Runnable {

    public static void main(String args[]){
        new Thread(new GameMusic()).start();


    }
    @Override
    public void run() {
// from a wave File
        Clip clip;
        AudioInputStream audioIn;
        File soundFile = new File("res/tetris.wav");
        try {
            audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }


    }

}
