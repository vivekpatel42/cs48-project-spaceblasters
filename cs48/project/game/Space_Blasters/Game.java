package cs48.project.game.Space_Blasters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.ArrayList;

public class Game extends Canvas{

	private ResourceManager rm;


	/**
	 * Construct our game and set it running.
	 */
	public Game() {
		// create a frame to contain our game
		rm = new ResourceManager();
		rm.GenerateEnemies();

		JFrame container = new JFrame("Space Blasters");

		// get hold the content of the frame and set up the resolution of the game

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);

		// setup our canvas size and put it into the content of the frame

		setBounds(0,0,800,600);
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














	/** The stragey that allows us to use accelerate page flipping */
	private BufferStrategy strategy;
	/** True if the game is currently "running", i.e. the game loop is looping */
	private boolean gameRunning = true;
	/** The list of all the entities that exist in our game */
	private ArrayList entities = new ArrayList();
	/** The list of entities that need to be removed from the game this loop */
	private ArrayList removeList = new ArrayList();
	/** The entity representing the player */
	/** The speed at which the player's ship should move (pixels/sec) */
	private double moveSpeed = 300;
	/** The time at which last fired a shot */
	private long lastFire = 0;
	/** The interval between our players shot (ms) */
	private long firingInterval = 500;
	/** The number of aliens left on the screen */
	private int alienCount;

	/** The message to display which waiting for a key press */
	private String message = "";
	/** True if we're holding up game play until a key has been pressed */
	private boolean waitingForKeyPress = true;
	/** True if the left cursor key is currently pressed */
	private boolean upPressed = false;
	/** True if the right cursor key is currently pressed */
	private boolean downPressed = false;
	/** True if the left cursor key is currently pressed */
	private boolean leftPressed = false;
	/** True if the right cursor key is currently pressed */
	private boolean rightPressed = false;
	/** True if we are firing */
	private boolean firePressed = false;
	/** True if game logic needs to be applied this loop, normally as a result of a game event */
	private boolean logicRequiredThisLoop = false;


	 public void gameLoop() {
	 long lastLoopTime = System.currentTimeMillis();

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
		 g.setColor(Color.black);
		 g.fillRect(0,0,800,600);


		 //Calls on the player class gto spawn a projectile. Currently does not include the projectile position
		 if (firePressed) {
			 Projectile shot = rm.getYou().TryToFire();
			 if (shot == null) ;
			 else
				 rm.getProjectileArr().add(shot);
		 }


		 // cycle round asking each entity to move itself

		 if (!waitingForKeyPress) {
			 for (int i=0;i<rm.getEnemyArr().size()-1;i++) {
				 rm.getEnemyArr().get(i).CalculateMove();

			 }
			 for (int i=0;i<rm.getProjectileArr().size()-1;i++) {
				 rm.getProjectileArr().get(i).CalculateMove();

			 }
		 }

		 // cycle round drawing all the entities we have in the game

		 for (int i=0; i<rm.getEnemyArr().size() -1 ;i++) {
			 g.drawImage(rm.getEnemyArr().get(i).getImage(), null, (int)rm.getEnemyArr().get(i).getxPos(), (int)rm.getEnemyArr().get(i).getyPos());
		 }
		 for (int i=0; i<rm.getProjectileArr().size()-1  ;i++) {
			 g.drawImage(rm.getProjectileArr().get(i).getImage(), null, (int)rm.getProjectileArr().get(i).getxPos(), (int)rm.getProjectileArr().get(i).getyPos());
		 }
		 g.drawImage(rm.getYou().getImage(), null, (int)rm.getYou().getxPos(), (int)rm.getYou().getyPos());




		 // DO COLLISION DETECTION LOOPS HERE




		/*

		 entities.removeAll(removeList);
		 removeList.clear();

		 // if a game event has indicated that game logic should

		 // be resolved, cycle round every entity requesting that

		 // their personal logic should be considered.

		 if (logicRequiredThisLoop) {
			 for (int i=0;i<entities.size();i++) {
				 Entity entity = (Entity) entities.get(i);
				 entity.doLogic();
			 }

			 logicRequiredThisLoop = false;
		 }
*/
		 // if we're waiting for an "any key" press then draw the
		 // current message

		 if (waitingForKeyPress) {
			 g.setColor(Color.white);
			 g.drawString(message,(800-g.getFontMetrics().stringWidth(message))/2,250);
			 g.drawString("Press space to start",(800-g.getFontMetrics().stringWidth("Press space to start"))/2,300);
		 }

		 // finally, we've completed drawing so clear up the graphics

		 // and flip the buffer over

		 g.dispose();
		 strategy.show();

		 // if we're pressing fire, attempt to fire





		 // resolve the movement of the ship. First assume the ship

		 // isn't moving. If either cursor key is pressed then

		 // update the movement appropraitely

		 //ship.setHorizontalMovement(0);

		 if ((leftPressed) && (!rightPressed)) {
			 rm.getYou().move(-1, 0);
		 } else if ((rightPressed) && (!leftPressed)) {
			 rm.getYou().move(1, 0);
		 }

		 if ((upPressed) && (!downPressed)) {
			 rm.getYou().move(0, -1);
		 } else if ((downPressed) && (!upPressed)) {
			 rm.getYou().move(0, 1);
		 }

		 // finally pause for a bit. Note: this should run us at about

		 // 100 fps but on windows this might vary each loop due to

		 // a bad implementation of timer

		 try { Thread.sleep(10); } catch (Exception e) {}
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
 *
 * This has been implemented as an inner class more through
 * habbit then anything else. Its perfectly normal to implement
 * this as seperate class if slight less convienient.
 *
 * @author Kevin Glass
 */
private class KeyInputHandler extends KeyAdapter {
	/** The number of key presses we've had while waiting for an "any key" press */
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
		// check if we've recieved any recently. We may

		// have had a keyType() event from the user releasing

		// the shoot or move keys, hence the use of the "pressCount"
		// counter.

		if (waitingForKeyPress) {
			if (pressCount == 1) {
				// since we've now recieved our key typed

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

	/**
	 * The entry point into the game. We'll simply create an
	 * instance of class which will start the display and game
	 * loop.
	 *
	 * @param argv The arguments that are passed into our game
	 */
	public static void main(String argv[]) {

		Game g =new Game();

		// Start the main game loop, note: this method will not

		// return until the game has finished running. Hence we are

		// using the actual main thread to run the game.

		g.gameLoop();
	}
}