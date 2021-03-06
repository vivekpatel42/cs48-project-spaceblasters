package cs48.project.game.Space_Blasters;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

/**
 * Created by Vivek on 5/9/2015.
 */
public class Instructions extends JPanel {

    private BufferedImage arrowKeys;
    private BufferedImage spacebar;
    private JLabel instructions;

    /**
     * Default no-arg constructor for Instructions panel.
     */
    public Instructions() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        instructions = new JLabel("Use arrow keys for moving player ship");
        add(instructions);
        instructions = new JLabel("Use spacebar to shoot");
        add(instructions);
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            arrowKeys = loader.loadImage("arrowKeys.png");
            spacebar = loader.loadImage("spacebar.png");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(arrowKeys, 110, 70, null);
        g.drawImage(spacebar, 195, 350, null);
    }
}
