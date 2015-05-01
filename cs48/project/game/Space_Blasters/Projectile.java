package cs48.project.game.Space_Blasters;

public class Projectile extends SpriteBase {
	boolean friendly = true;

	public void CalculateMove(){
		if (friendly){
			move(0, -4);
		}
		else
			move (0, 4);
	}

	public Projectile() {
		super();
		GetSprite("bullet");
	}

	public Projectile(double xPos, double yPos) {
		super(xPos, yPos);
		GetSprite("bullet");
	}

	public Projectile(String type) {
		super();
		GetSprite("bullet");
		this.type = type;
	}

}
