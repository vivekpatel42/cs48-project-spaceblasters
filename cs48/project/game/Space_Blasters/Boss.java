package cs48.project.game.Space_Blasters;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by richa_000 on 5/31/2015.
 */
public class Boss extends SpriteBase{

    public long lastFire = 0;
    public long firingInterval = 500;
    private int hp;
    private double Xdirection=1;
    private double Ydirection=1;
    private BufferedImage spriteSheet = null;
    SpriteSheet ss;

    public Boss(){
        super(100, -50);
        hp = 100000;
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("Sprite.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ss = new SpriteSheet(spriteSheet);
        this.setImage(ss.grabImage( 5, 3, 95, 96));
    }
    public boolean gotShot(){
        this.hp -= 5000;
        if (this.hp <= 0){
            new Thread(new ExplosionAudio()).start();
            return true;
	}

        else return false;
    }


    public void TryToFire (ResourceManager rm){
        // check that we have waiting long enough to fire
        if (System.currentTimeMillis() - lastFire < firingInterval) {
            return;
        }
        // if we waited long enough, create the shot entity, and record the time.
	//new Thread(new BossLaserAudio()).start();

	lastFire = System.currentTimeMillis();
        for (int i= 0; i <= 2;i++ ){
            Projectile BossShot = new Projectile(this.getXPos()-50+100*i, this.getYPos(), false);
            BossShot.setImage(ss.grabImage(8, 3,27, 58));
            rm.getProjectileArr().add(BossShot);
        }
    }
    public void CalculateMove() {
            move(Xdirection, Ydirection);
            if (this.getXPos() < 50) {
                Xdirection = 2;
            } else if (this.getXPos() > 750)
                Xdirection = -2;
            if (this.getYPos() < 50) {
                Ydirection = 1;
            } else if (this.getYPos() > 150)
                Ydirection = -1;

    }
}
