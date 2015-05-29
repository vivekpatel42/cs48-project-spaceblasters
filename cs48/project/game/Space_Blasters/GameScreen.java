package cs48.project.game.Space_Blasters;

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
    private BufferedImage background = null;

    private BufferedImage player;
    private BufferedImage pbullet;
    private BufferedImage player2;
    private BufferedImage p2bullet;
    
    private BufferedImage enemy;
    private BufferedImage ebullet;
    private BufferedImage enemy2;
    private BufferedImage e2bullet;
    private BufferedImage enemy3;
    private BufferedImage e3bullet;

    private BufferedImage comet;
    
    private BufferedImage explosion; 
    public void init(){
	BufferedImageLoader loader = new BufferedImageLoader();
	try{
	    spriteSheet = loader.loadImage("Sprite.png");
	    background = loader.loadImage("background.png");
	    }
	catch(IOException e){
	    e.printStackTrace();
	}
	
      	SpriteSheet ss = new SpriteSheet(spriteSheet);
       	player = ss.grabImage(1,1,32,32);
	pbullet = ss.grabImage(2, 1, 9, 19);
	player2 = ss.grabImage(1, 2, 32, 23);
	p2bullet = ss.grabImage(2, 2, 11, 14);

	enemy = ss.grabImage(3, 1, 24, 25);
	ebullet = ss.grabImage(4, 1, 7, 17);
	enemy2 = ss.grabImage(5, 1, 26, 28);
	e2bullet = ss.grabImage(6, 1, 13, 13);
	enemy3 = ss.grabImage(3, 2, 32, 24);
	e3bullet = ss.grabImage(4, 2, 9, 10);

	comet = ss.grabImage(7, 1, 22, 32);

	explosion = ss.grabImage(8, 1, 32, 32);
       
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
	g.drawImage(background, 0, 0, null);
	
      	g.drawImage(player, 200, 400, this);
	g.drawImage(pbullet, 210, 375, this);
	g.drawImage(player2, 250, 400, this);
	g. drawImage(p2bullet, 260, 375, this);
	g.drawImage(explosion, 300, 400, this);

	g.drawImage(enemy, 100, 100, this);
	g.drawImage(ebullet, 110, 125, this);
	g.drawImage(enemy2, 150, 100, this);
	g.drawImage(e2bullet, 158, 145, this);
	g.drawImage(enemy3, 200, 100, this);
	g.drawImage(e3bullet, 212, 125, this);
	g.drawImage(comet, 250, 100, this);
	
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

