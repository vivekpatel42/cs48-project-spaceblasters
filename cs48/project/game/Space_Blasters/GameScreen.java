import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import java.lang.InterruptedException;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;

/*
* author Gabriel Romero, 5/2/15
* tutorial given by RealTutsGML 
* gameloop help from Stack OVerflow
*  
*/


public class GameScreen extends Canvas implements Runnable{
    
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH/12*9;
    public static final int SCALE = 2;
    public final String TITLE = "Space Blasters!";
    private boolean running =  false;
    private Thread thread;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    
    private BufferedImage player;
    private BufferedImage enemy;
    private BufferedImage bullet1;
    private BufferedImage bullet2;

    public void init(){
	BufferedImageLoader loader = new BufferedImageLoader();
	try{
	    spriteSheet = loader.loadImage("GABE.png");
	    }
	catch(IOException e){
	    e.printStackTrace();
	}
	
      	SpriteSheet ss = new SpriteSheet(spriteSheet);
       	player = ss.grabImage(1,1,32,33);
	enemy = ss.grabImage(3,1,32,32);
	bullet1 = ss.grabImage(2,1,32,32);
	bullet2 = ss.grabImage(4,1,32,32);
    }
    private synchronized void start(){
	if(running)
	    return;
        running = true;
        thread = new Thread(this);
        thread.start();
	
    }
    
    private synchronized void stop(){
	if(!running)
	    return;
	running = false;
	try{
	    thread.join();
	} catch (InterruptedException e){
	    e.printStackTrace();
	}
	System.exit(1);
    }

    public void run(){
	init();
	long last = System.nanoTime();
	final double ticks = 60.0;
	double ns = 1000000000/ticks;
	double delta = 0;
	int update = 0;
	int frame = 0;
	long timer = System.currentTimeMillis();
	
	while (running){
	    long now = System.nanoTime();
	    delta += (now - last)/ns;
	    last = now;
	    if(delta >=1){
		tick();
		update++;
		delta--;
	    }
	    render();
	    frame++;
	    
	    if(System.currentTimeMillis() - timer > 1000){
		timer += 1000;
		System.out.println(update + "ticks , fps" + frame);
		update = 0;
		frame = 0;
	    }
	}
	stop();
    }
    
    public void tick(){
    
    }
    
    public void render(){
	
	BufferStrategy bs = this.getBufferStrategy(); 
	
	if(bs == null){
	    createBufferStrategy(3);
	    return;
	}
	
	Graphics g = bs.getDrawGraphics();
	g.drawImage(image, 0 , 0 , getWidth() , getHeight() , this);
	
      	g.drawImage(player, 200, 400, this);
	g.drawImage(enemy, 100, 100, this);
	g.drawImage(bullet1, 200, 300, this);
	g.drawImage(bullet2, 100, 200, this);

	g.dispose();
	bs.show();
    }
    public static void main(String[] args){
	GameScreen game = new GameScreen();
	game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	
	JFrame frame = new JFrame(game.TITLE);
	frame.add(game);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	game.start();
    }
    
    
}

