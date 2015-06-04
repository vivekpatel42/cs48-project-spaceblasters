package cs48.project.game.Space_Blasters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Richard Alvarez on 4/30/2015.
 */

public class Menu extends JPanel implements ActionListener{
    /*
     * Creating an object of JFrame instead of extending it
     * has no side effects.
     */
    private JFrame frame;
    private Frame gameFrame;
    private JPanel panel;
    private Game gameCanvas;
    private JButton ReturnMenu;
    private JButton StartGame, Instructions, highScores;
    private BufferedImage menuBackground;

    public Menu() {

        super();
        panel = new JPanel();
        panel.setBackground(Color.black);
        ReturnMenu = new JButton("BACK");
        ReturnMenu.addActionListener(this);
        panel.add(ReturnMenu);

/*This block of code sets the properties of the menu panel
 */
        this.setSize(800, 600);
        StartGame = new JButton("Start Game");
        StartGame.addActionListener(this);
        highScores = new JButton("High Scores");
        highScores.addActionListener(this);
        Instructions = new JButton("Instructions");
        Instructions.addActionListener(this);
        //sound = new JButton("Music");
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            menuBackground = loader.loadImage("MenuBack.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(StartGame, BorderLayout.CENTER);
        this.add(highScores, BorderLayout.CENTER);
        this.add(Instructions, BorderLayout.CENTER);
        frame = new JFrame("Space Blasters");
        // If you running your program from cmd, this line lets it comes
        // out of cmd when you click the top-right  RED Button.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBackground, 0, 0, null);
    }

    public void actionPerformed(ActionEvent ae) {
        JButton button = (JButton) ae.getSource();
        if (button == StartGame) {
            panel = (JPanel) frame.getContentPane();
            panel.setPreferredSize(new Dimension(800, 600));
            panel.setLayout(null);
            gameCanvas = new Game();
            panel.add(gameCanvas);
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        } else if (button == Instructions) {
            frame.remove(this);
            panel = new Instructions();
            panel.add(ReturnMenu);
            frame.setContentPane(panel);
        } else if (button == highScores) {
            frame.remove(this);
            panel = new HighScores();
            panel.add(ReturnMenu);
            frame.setContentPane(panel);
        } else if (button == ReturnMenu) {
            frame.remove(panel);
            panel = new JPanel();
            panel.setBackground(Color.black);
            panel.add(ReturnMenu);
            frame.setContentPane(this);
        }
        frame.validate();
        frame.repaint(); // prefer to write this always.

    }

    public static void main(String[] args) {
        /*
         * This is the most important part of your GUI app, never forget
         * to schedule a job for your event dispatcher thread :
         * by calling the function, method or constructor, responsible
         * for creating and displaying your GUI.
         */
       Thread music =  new Thread(new MenuMusic());
        music.start();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu();
            }
        });
    }
}