package cs48.project.game.Space_Blasters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends SpriteBase {

	public long lastFire = 0;
	public long firingInterval = 700;
	private int lvl;
	private int hp;
	private int movement;
	private double Xdirection=1;
	private double Ydirection=1;
	private BufferedImage spriteSheet = null;


	public Enemy(double xDelta, double yDelta){
		super(xDelta,yDelta);
		lvl = 1;
		hp = lvl*100;
		movement = 3;
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("sprite_sheet.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		this.setImage(ss.grabImage(3, 1, 32, 32));
	}

	public Enemy(double xDelta, double yDelta, int move){
		super(xDelta,yDelta);
		lvl = 1;
		hp = lvl*100;
		movement = move;
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("Sprite.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		if (move == 2)
		this.setImage(ss.grabImage(5, 1, 32, 32));
		else {
			this.setImage(ss.grabImage(3, 1, 24, 25));

		}
	}

	public Projectile TryToFire (){
		// check that we have waiting long enough to fire
		if (System.currentTimeMillis() - lastFire < firingInterval) {
			return null;
		}
		// if we waited long enough, create the shot entity, and record the time.
		lastFire = System.currentTimeMillis();
		return new Projectile(this.getXPos(), this.getYPos(), 4);
	}
	public void CalculateMove() {
		if (this.movement == 1) {
			Ydirection = 0;
			move(Xdirection, Ydirection);
			if (this.getXPos() < 50) {
				Xdirection = 1;
			} else if (this.getXPos() > 750)
				Xdirection = -1;
		}
        else if (this.movement == 2) {
            move(Xdirection, Ydirection);
            if (this.getXPos() < 50) {
                Xdirection = 1;
            } else if (this.getXPos() > 750)
                Xdirection = -1;
            if (this.getYPos() < 50) {
                Ydirection = 1;
            } else if (this.getYPos() > 150)
                Ydirection = -1;
        }
        else if (this.movement == 3) {
			move(Xdirection, Ydirection);
			if (this.getXPos() < 50) {
				Xdirection = 1;
				Ydirection = 0;
			} else if (this.getXPos() > 750) {
				Xdirection = -1;
				Ydirection =1;
                if (this.getYPos() > 750)
                    Ydirection = -1;
                else if (this.getYPos() < 50)
                    Ydirection = 1;
			}
        }
	}
}
