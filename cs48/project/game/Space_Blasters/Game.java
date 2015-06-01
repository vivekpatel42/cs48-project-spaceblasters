package cs48.project.game.Space_Blasters;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


/**
 * @author Richard Alvarez
 */

public class Game extends Canvas {

    private ResourceManager rm;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage background = null;
    private JPanel gameOver;
    private JFrame container;

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            //   spriteSheet = loader.loadImage("Sprite.png");
            background = loader.loadImage("background.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Construct our game and set it running.
     */

    public Game() {
        rm = new ResourceManager();
        rm.GenerateEnemies();

        // create a frame to contain our game

        container = new JFrame("Space Blasters");

        // get hold the content of the frame and set up the resolution of the game

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(HEIGHT, WIDTH));
        panel.setLayout(null);


        // setup our canvas size and put it into the content of the frame

        setBounds(0, 0, HEIGHT, WIDTH);
        panel.add(this);

        // Tell AWT not to bother repainting our canvas since we're

        // going to do that our self in accelerated mode

        setIgnoreRepaint(true);

        // finally make the window visible

        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        // add a listener to respond to the user closing the window. If they

        // do we'd like to exit the game

        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // add a key input system (defined below) to our canvas

        // so we can respond to key pressed

        addKeyListener(new KeyInputHandler());

        // request the focus so key events come to us

        requestFocus();

        // create the buffering strategy which will allow AWT

        // to manage our accelerated graphics

        createBufferStrategy(2);
        strategy = getBufferStrategy();


    }


    /**
     * The stragey that allows us to use accelerate page flipping
     */
    private BufferStrategy strategy;
    /**
     * True if the game is currently "running", i.e. the game loop is looping
     */
    private boolean gameRunning = true;

    /**
     * The message to display which waiting for a key press
     */
    private String message = "";
    /**
     * True if we're holding up game play until a key has been pressed
     */
    private boolean waitingForKeyPress = true;
    /**
     * True if the left cursor key is currently pressed
     */
    private boolean upPressed = false;
    /**
     * True if the right cursor key is currently pressed
     */
    private boolean downPressed = false;
    /**
     * True if the left cursor key is currently pressed
     */
    private boolean leftPressed = false;
    /**
     * True if the right cursor key is currently pressed
     */
    private boolean rightPressed = false;
    /**
     * True if we are firing
     */
    private boolean firePressed = false;

    private long playerScore;

    public void gameLoop() {
        init();
        startGame();
        long lastLoopTime = System.currentTimeMillis();
        Boss NotFound = null;
        int wave= 0;
        Random rand = new Random();
        gameRunning = true;
        rm = new ResourceManager();
        waitingForKeyPress = true;
        // keep looping round til the game ends
        while (gameRunning) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop

            long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();

            // Get hold of a graphics context for the accelerated

            // surface and blank it out

            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g.drawImage(background, 0, 0, null);
            playerScore = rm.getMainPlayer().getScore();
            g.setColor(Color.GREEN);
            g.drawString("Score: " + Long.toString(playerScore) + "  Health: " + rm.getMainPlayer().getHp(), 30, 575);

            if (rm.getEnemyArr().size() == 0) {
                rm.GenerateEnemies(rand.nextInt(3));
                wave++;
            }
            //Generate random meteors
            int meteor = rand.nextInt(1000);
            if (!waitingForKeyPress && meteor <=4 && wave > 5){
                Projectile Meteor = new Projectile(rand.nextInt(700), 0, rand.nextInt(2)+7);
                Meteor.setDirection(rand.nextDouble()-.5);
                rm.getProjectileArr().add(Meteor);

            }


            if (!waitingForKeyPress){//asks resource manager to remove bullets out of frame
                rm.CleanBullets();
            }




            //LOOPS FOR THE BOSS TO SPAWN AND ACT
            if (!waitingForKeyPress && (wave == 3 || wave == 7 || NotFound!= null)){
                if (NotFound == null) {
                    NotFound = new Boss();
                }
                if (wave ==7){
                    NotFound.firingInterval = 350;
                }
                    NotFound.CalculateMove();
                    NotFound.TryToFire(rm);
                    g.drawImage(NotFound.getImage(), null, (int) NotFound.getXPos(), (int) NotFound.getYPos());
                //COLLISION FOR BOSS
                boolean isDead = false;
                    ArrayList<Projectile> toDeleteShot = new ArrayList<Projectile>(); //COLLISION DETECTION FOR FRIENDLY PROJECTILES TO ENEMIES
                    for (int i = 0; i < rm.getProjectileArr().size(); i++) {
                        if (rm.getProjectileArr().get(i).collidesWith(NotFound) && rm.getProjectileArr().get(i).isFriendly()) {
                            toDeleteShot.add(rm.getProjectileArr().get(i));
                            if (NotFound.gotShot())
                                isDead = true;
                        }
                    }
                    for (int i = 0; i < toDeleteShot.size(); i++) {
                        rm.getProjectileArr().remove(toDeleteShot.get(i));
                    }
                    if (isDead) {
                        NotFound = null;
                        rm.getMainPlayer().increaseScore(25000);
                    }
            }

            // resolve the movement of the ship. First assume the ship

            // isn't moving. If either cursor key is pressed then

            // update the movement appropriately


            if (((leftPressed) && (!rightPressed)) && rm.getMainPlayer().getXPos() > 0) {
                rm.getMainPlayer().move(-1, 0);
            } else if ((rightPressed) && (!leftPressed) && rm.getMainPlayer().getXPos() < 768) {
                rm.getMainPlayer().move(1, 0);
            }

            if ((upPressed) && (!downPressed) && rm.getMainPlayer().getYPos() > 0) {
                rm.getMainPlayer().move(0, -1);
            } else if ((downPressed) && (!upPressed) && rm.getMainPlayer().getYPos() < 568) {
                rm.getMainPlayer().move(0, 1);
            }


            // cycle round asking each entity to move itself

            if (!waitingForKeyPress) {
                for (int i = 0; i < rm.getEnemyArr().size(); i++) {
                    rm.getEnemyArr().get(i).CalculateMove();

                }
                for (int i = 0; i < rm.getProjectileArr().size(); i++) {
                    rm.getProjectileArr().get(i).CalculateMove();
                }
            }

            // if we're pressing fire, attempt to fire

            //Calls on the player class to spawn a projectile. Currently does not include the projectile position
            if (firePressed) {
                Projectile shot = rm.getMainPlayer().TryToFire();
                if (!(shot == null))
                    rm.getProjectileArr().add(shot);
            }
            for (int i = 0; i < rm.getEnemyArr().size(); i++) {
                Projectile shot = rm.getEnemyArr().get(i).TryToFire();
                if (!(shot == null))
                    rm.getProjectileArr().add(shot);
            }


            // cycle round drawing all the entities we have in the game

            for (int i = 0; i < rm.getEnemyArr().size(); i++) {
                g.drawImage(rm.getEnemyArr().get(i).getImage(), null, (int) rm.getEnemyArr().get(i).getXPos(), (int) rm.getEnemyArr().get(i).getYPos());
            }
            for (int i = 0; i < rm.getProjectileArr().size(); i++) {
                g.drawImage(rm.getProjectileArr().get(i).getImage(), null, (int) rm.getProjectileArr().get(i).getXPos(), (int) rm.getProjectileArr().get(i).getYPos());
            }
            g.drawImage(rm.getMainPlayer().getImage(), null, (int) rm.getMainPlayer().getXPos(), (int) rm.getMainPlayer().getYPos());


            // DO COLLISION DETECTION by calling the resource manager
            if (!waitingForKeyPress && !(rm.getProjectileArr() == null)) {
                rm.Collisions();
            }


            // if we're waiting for an "any key" press then draw the
            // current message

            if (waitingForKeyPress) {
                g.setColor(Color.white);
                g.drawString(message, (800 - g.getFontMetrics().stringWidth(message)) / 2, 250);
                g.drawString("Press space to start", (800 - g.getFontMetrics().stringWidth("Press space to start")) / 2, 300);
            }

            //Check for Game ending condition
            if (rm.checkForGameOver()) {
                gameRunning = false;
                BufferedImageLoader loader = new BufferedImageLoader();
                try {
                    background = loader.loadImage("GameOver.png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(background, 0, 0, null);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }

            // finally, we've completed drawing so clear up the graphics

            // and flip the buffer over

            g.dispose();
            strategy.show();

            // finally pause for a bit. Note: this should run us at about
            try {
                Thread.sleep(12);
            } catch (Exception e) {
            }
        }

    }


    /**
     * Start a fresh game, this should clear out any old data and
     * create a new set.
     */
    private void startGame() {
        // blank out any keyboard settings we might currently have
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        firePressed = false;
    }

    /**
     * A class to handle keyboard input from the user. The class
     * handles both dynamic input during game play, i.e. left/right
     * and shoot, and more static type input (i.e. press any key to
     * continue)
     * <p/>
     * This has been implemented as an inner class more through
     * habit then anything else. Its perfectly normal to implement
     * this as separate class if slight less convenient.
     *
     * @author Kevin Glass
     */
    private class KeyInputHandler extends KeyAdapter {
        /**
         * The number of key presses we've had while waiting for an "any key" press
         */
        private int pressCount = 1;

        /**
         * Notification from AWT that a key has been pressed. Note that
         * a key being pressed is equal to being pushed down but *NOT*
         * released. Thats where keyTyped() comes in.
         *
         * @param e The details of the key that was pressed
         */
        public void keyPressed(KeyEvent e) {
            // if we're waiting for an "any key" typed then we don't
            // want to do anything with just a "press"
            if (waitingForKeyPress) {
                return;
            }

            if (e.getKeyCode() == KeyEvent.VK_UP)
                upPressed = true;
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                downPressed = true;
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                firePressed = true;
            }
        }

        /**
         * Notification from AWT that a key has been released.
         *
         * @param e The details of the key that was released
         */
        public void keyReleased(KeyEvent e) {
            // if we're waiting for an "any key" typed then we don't
            // want to do anything with just a "released"
            if (waitingForKeyPress) {
                return;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP)
                upPressed = false;
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                downPressed = false;
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                firePressed = false;
            }
        }

        /**
         * Notification from AWT that a key has been typed. Note that
         * typing a key means to both press and then release it.
         *
         * @param e The details of the key that was typed.
         */
        public void keyTyped(KeyEvent e) {
            // if we're waiting for a "any key" type then
            // check if we've received any recently. We may

            // have had a keyType() event from the user releasing

            // the shoot or move keys, hence the use of the "pressCount"
            // counter.

            if (waitingForKeyPress) {
                if (pressCount == 1) {
                    // since we've now received our key typed

                    // event we can mark it as such and start

                    // our new game

                    waitingForKeyPress = false;
                    startGame();
                    pressCount = 0;
                } else {
                    pressCount++;
                }
            }

            // if we hit escape, then quit the game

            if (e.getKeyChar() == 27) {
                System.exit(0);
            }
        }
    }

        protected void checkForHighScore() {
            HighScores hs = new HighScores();
            long[] scoreList = hs.getScoreList();
            for (int i = 0; i < 10; i++) {
                if (rm.getMainPlayer().getScore() > scoreList[i]) {
                    writeNewHighScore(hs, i);
                }
            }
        }

    private void writeNewHighScore(final HighScores hs, int i) {
        final JFrame frame = new JFrame("New High Score!");
        JPanel highScoreEntry = new JPanel();
        highScoreEntry.setPreferredSize(new Dimension(640, 480));
        highScoreEntry.setLayout(new BoxLayout(highScoreEntry, BoxLayout.PAGE_AXIS));
        JLabel newHighScore = new JLabel("Your score, " + rm.getMainPlayer().getScore() + ", is the new #" + (i + 1) + " score!");
        JLabel pressEnter = new JLabel("Enter your name and press enter to submit your score.");
        final JTextField enterName = new JTextField(15);
        highScoreEntry.add(newHighScore);
        highScoreEntry.add(pressEnter);
        highScoreEntry.add(enterName);
        frame.add(highScoreEntry);
        enterName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = enterName.getText();
                hs.writeHighScore(name, rm.getMainPlayer().getScore());
                frame.setVisible(false);
            }
        });
        frame.setVisible(true);
    }


    /**
         * The entry point into the game. We'll simply create an
         * instance of class which will start the display and game
         * loop.
         *
         * @param args The arguments that are passed into our game
         */
    /*
        public static void main(String ... args) {
            Game g = new Game();
            new Thread(new GameMusic()).start();
            // Start the main game loop, note: this method will not

            // return until the game has finished running. Hence we are

            // using the actual main thread to run the game.
            boolean loop = true;
            while (loop) {
                g.gameLoop();
                g.checkForHighScore();
            }
        }
*/
    public static void go() {
        Game g = new Game();
        new Thread(new GameMusic()).start();
        // Start the main game loop, note: this method will not


        // return until the game has finished running. Hence we are

        // using the actual main thread to run the game.
        boolean loop = true;
        while (loop) {
            g.gameLoop();
            g.checkForHighScore();
            //loop = false;
        }

    }
    public static void main(String[] args) {
        Game.go();
    }
}
