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
	private double Ydirection=0;
	private BufferedImage spriteSheet = null;


	public Enemy() {
		super();
		lvl = 1;
		hp = lvl*100;
		movement = 1;
		GetSprite("AngryFace");
	}
	public Enemy(double xDelta, double yDelta){
		super(xDelta,yDelta);
		lvl = 1;
		hp = lvl*100;
		movement = 1;
		//GetSprite("AngryFace");
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("sprite_sheet.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		this.setImage(ss.grabImage(3, 1, 32, 32));
	}
	public Projectile TryToFire (){
		// check that we have waiting long enough to fire
		if (System.currentTimeMillis() - lastFire < firingInterval) {
			return null;
		}
		// if we waited long enough, create the shot entity, and record the time.
		lastFire = System.currentTimeMillis();
		return new Projectile(this.getXPos(), this.getYPos(), false);
	}
	//Stub
	public void CalculateMove() {
		if (this.movement == 1) {
			move(Xdirection, Ydirection);
			if (this.getXPos() < 50) {
				Xdirection = 1;
			} else if (this.getXPos() > 750)
				Xdirection = -1;

		}
	}
}
