package cs48.project.game.Space_Blasters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile extends SpriteBase {
	boolean friendly = true;
	private BufferedImage spriteSheet = null;

	public boolean isFriendly() {
		return friendly;
	}

	public void CalculateMove(){
		if (friendly){

			move(0, -1.2);
		}
		else
			move (0, 1.1);
	}

	public Projectile(double xPos, double yPos, boolean friend) {
		super(xPos, yPos);
		BufferedImageLoader loader = new BufferedImageLoader();
		friendly = friend;
		try {
			spriteSheet = loader.loadImage("sprite_sheet.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		int bulletChoice = 2;
		if (!(friendly))
			bulletChoice = 4;
		this.setImage(ss.grabImage(bulletChoice, 1, 32, 33));
	}

	public Projectile(double xPos, double yPos) {
		super(xPos, yPos);
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("sprite_sheet.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Projectile not Found");
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		this.setImage(ss.grabImage(2, 1, 32, 33));
	}

	public Projectile(String type) {
		super();
		GetSprite("bullet");
		this.type = type;
	}

}
