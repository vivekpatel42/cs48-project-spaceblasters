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

    private BufferedImage wasd;
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
            wasd = ImageIO.read(new File("res/wasd.png"));
            spacebar = ImageIO.read(new File("res/spacebar.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(wasd, 250, 100, null);
        g.drawImage(spacebar, 250, 320, null);
    }
}
