package cs48.project.game.Space_Blasters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile extends SpriteBase {
	boolean friendly = true;
	private BufferedImage spriteSheet = null;
	private double direction= 0;


	public void setDirection(double direction) {
		this.direction = direction;
	}

	public boolean isFriendly() {
		return friendly;
	}

	public void CalculateMove(){
		if (friendly){

			move(0, -1.2);
		}
		else
			move (0, 1.1);
		move(direction, 0);
	}

	public Projectile(double xPos, double yPos, int bullet) {
		super(xPos, yPos);
		friendly = false;
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("Sprite.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		int bulletChoice = bullet;
		this.setImage(ss.grabImage(bulletChoice, 1, 32, 33));
	}

	public Projectile(double xPos, double yPos) {
		super(xPos, yPos);
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("Sprite.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Projectile not Found");
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		this.setImage(ss.grabImage(2, 1, 32, 33));
	}

	public Projectile(double xPos, double yPos, boolean friend) {
		super(xPos, yPos);
		friendly = friend;
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("Sprite.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Projectile not Found");
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		this.setImage(ss.grabImage(2, 1, 32, 33));
	}


}
