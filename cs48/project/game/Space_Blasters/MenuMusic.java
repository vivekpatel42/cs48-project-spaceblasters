package cs48.project.game.Space_Blasters;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Created by richa_000 on 5/19/2015.
 */
public class MenuMusic implements Runnable {

    private volatile boolean done = false;

    public static void main(String args[]) {
        new Thread(new GameMusic()).start();


    }

    public void interrupt() {
    }

    public void stopExecuting() {

        this.done = true;
    }

    @Override
    public void run() {
// from a wave File
        Clip clip;
        AudioInputStream audioIn;
//        File soundFile = new File("res/MenuTheme.wav");
        try {
            audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("/res/MenuTheme.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            while (!Thread.currentThread().isInterrupted()) ;
            clip.stop();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }


}
