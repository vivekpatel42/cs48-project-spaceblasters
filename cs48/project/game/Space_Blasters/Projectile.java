package cs48.project.game.Space_Blasters;

public class Projectile extends SpriteBase {

	public Projectile() {
		super();
		GetSprite("Projectile");
	}
	
	public Projectile(String type) {
		super();
		GetSprite("Projectile");
		this.type = type;
	}

}
