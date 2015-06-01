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
        instructions = new JLabel("Use \"WASD\" or arrow keys for moving player ship");
        add(instructions);
        instructions = new JLabel("Use spacebar to shoot");
        add(instructions);
        try {
            arrowKeys = ImageIO.read(new File("arrowKeys.png"));
            spacebar = ImageIO.read(new File("/spacebar.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(arrowKeys, 250, 100, null);
        g.drawImage(spacebar, 250, 320, null);
    }
}
