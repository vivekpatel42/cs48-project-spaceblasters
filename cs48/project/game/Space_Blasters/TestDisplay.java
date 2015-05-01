package cs48.project.game.Space_Blasters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Richard Alvarez on 4/30/2015.
 */

public class TestDisplay implements ActionListener{
    /*
     * Creating an object of JFrame instead of extending it
     * has no side effects.
     */
    private JFrame frame;
    private JPanel menu, panel;
    private JButton ReturnMenu;
    private JButton StartGame, Instructions, Highscores, Options;
    public TestDisplay()
    {



        panel = new JPanel();
        panel.setBackground(Color.black);
        ReturnMenu = new JButton("BACK");
        ReturnMenu.addActionListener(this);
        panel.add(ReturnMenu);

/*This block of code sets the properties of the menu panel
 */
        menu = new JPanel();
        menu.setSize(800, 600);
        menu.setBackground(Color.BLACK);
        StartGame = new JButton("Start Game");
        StartGame.addActionListener(this);
        Highscores = new JButton("High Scores");
        Highscores.addActionListener(this);
        Instructions = new JButton("Instructions");
        Instructions.addActionListener(this);
        Options = new JButton("Options");
        Options.addActionListener(this);
        //sound = new JButton("Music");
        menu.add(StartGame, BorderLayout.CENTER);
        menu.add(Highscores, BorderLayout.CENTER);
        menu.add(Instructions, BorderLayout.CENTER);
        menu.add(Options, BorderLayout.CENTER);



        frame = new JFrame("Frame Display Test");
        // If you running your program from cmd, this line lets it comes
        // out of cmd when you click the top-right  RED Button.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(menu);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        JButton button = (JButton) ae.getSource();
            if (button == StartGame)
            {
                /*
                 * this will remove the first panel
                 * and add the new panel to the frame.
                 */
                frame.remove(menu);
                frame.setContentPane(panel);
            }
            else if (button  == Instructions)
            {
                frame.remove(menu);
                frame.setContentPane(panel);
            }
            else if (button  == Highscores)
            {
                frame.remove(menu);
                frame.setContentPane(panel);
            }
            else if (button  == Options)
            {
                frame.remove(menu);
                frame.setContentPane(panel);
            }
            else if (button  == ReturnMenu)
            {
                frame.remove(panel);
                frame.setContentPane(menu);
            }
            frame.validate();
            frame.repaint(); // prefer to write this always.

    }

    public static void main(String[] args)
    {
        /*
         * This is the most important part ofyour GUI app, never forget
         * to schedule a job for your event dispatcher thread :
         * by calling the function, method or constructor, responsible
         * for creating and displaying your GUI.
         */
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TestDisplay();
            }
        });
    }
}