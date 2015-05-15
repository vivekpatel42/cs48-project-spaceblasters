package cs48.project.game.Space_Blasters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends SpriteBase {

    public long lastFire = 0;
    public long firingInterval = 400;
	private int hp;
	private int exp;
    private long score = 0;
    private BufferedImage spriteSheet = null;

    public Player(double xPos, double yPos) {
        super(xPos, yPos);
        //GetSprite("Player");
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("sprite_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.setImage(ss.grabImage(1, 1, 32, 33));
        this.setType("Player");
        exp = 0;
        hp = 100;
    }
    public boolean GotShot(){
        hp -= 50;
        if (hp <= 0)
            return true;
        return false;
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

    public long getScore() {
        return this.score;
    }

    public int getHp() {
        return hp;
    }

    public void increaseScore(long score) {
        this.score += score;
    }

    @Override
    public boolean friendlyCollision(SpriteBase other) {
        return true; //STUB
    }
}
