package cs48.project.game.Space_Blasters;

public class Player extends SpriteBase {

    public long lastFire = 0;
    public long firingInterval = 400;
	private int lvl;
	private int hp;
	private int exp;
	
    public Player(double xPos, double yPos) {
        super(xPos, yPos);
        GetSprite("Player");
        this.setType("Player");
        exp = 0;
        hp = 100;
    }

    public Player() {
        super();
        GetSprite("Player");
        this.setType("Player");
        exp = 0;
        hp = 100;
    }
    public Projectile TryToFire (){
        // check that we have waiting long enough to fire

        if (System.currentTimeMillis() - lastFire < firingInterval) {
            return null;
        }

        // if we waited long enough, create the shot entity, and record the time.

        lastFire = System.currentTimeMillis();
        return new Projectile(this.getXPos(), this.getYPos());
    }
    @Override
    public boolean friendlyCollision(SpriteBase other) {
        return true; //STUB
    }
}
