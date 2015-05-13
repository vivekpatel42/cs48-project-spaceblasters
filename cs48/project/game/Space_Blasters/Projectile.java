package cs48.project.game.Space_Blasters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile extends SpriteBase {
	boolean friendly = true;
	private BufferedImage spriteSheet = null;

	public void CalculateMove(){
		if (friendly){
			move(0, -1.2);
		}
		else
			move (0, 3);
	}

	public Projectile() {
		super();
		GetSprite("bullet");
	}

	public Projectile(double xPos, double yPos) {
		super(xPos, yPos);
		//GetSprite("bullet");
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("GABE.png");
		} catch (IOException e) {
			e.printStackTrace();
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
