package cs48.project.game.Space_Blasters;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Created by richa_000 on 6/1/2015.
 */
public class FIRINGMALASER implements Runnable {

    public static void main(String args[]) {
        new Thread(new FIRINGMALASER()).start();


    }

    @Override
    public void run() {
        // from a wave File
        Clip clip;
        AudioInputStream audioIn;
//        File soundFile = new File("res/LOL.wav");
        try {
            audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("/res/LOL.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(0);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }


    }
}
