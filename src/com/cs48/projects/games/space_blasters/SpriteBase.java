import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Richard Alvarez
 */


public abstract class SpriteBase {



    private double xPos;
    private double yPos;
    private double speed;
    private BufferedImage image;

    /** The rectangle used for this entity during collisions  resolution */
    private Rectangle me = new Rectangle();
    /** The rectangle used for other entities during collision resolution */
    private Rectangle him = new Rectangle();

    public BufferedImage getImage() {
        return image;
    }
    public double getSpeed() {
        return speed;
    }
    public double getyPos() {
        return yPos;
    }
    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Constructor to determine initial position
     * @param xPos Initial Xposition
     * @param yPos Initial Yposition
     */
    public SpriteBase(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = 1.0;
        this.image = null;
    }

    public SpriteBase() {
        this.xPos = 0;
        this.yPos = 0;
        this.speed = 1.0;
        this.image = null;
    }

    /**
     * Changes the X and Y coordinate based on input
     * @param Xdelta    amount to change X
     * @param Ydelta    amount to change y
     */
    public void move (double Xdelta, double Ydelta) {
        xPos += Xdelta * speed;
        yPos += Ydelta * speed;
    }

    /**
     * returns true if the image is set and false in IOException
     * @param SpriteName The name of the file to attempt and load
     */
    public boolean GetSprite(String SpriteName) {
        try {
            image = ImageIO.read(new File(SpriteName + ".jpg"));
        } catch (IOException e) {
                return false;
        }
        return true;
    }

    /**
     *
     * @param other the other sprite
     * @return true if collision is detected collision is determined by the abstract method collidedWith
     */
    public boolean collidesWith(SpriteBase other) {
        me.setBounds((int) xPos, (int) yPos, image.getWidth(), image.getHeight());
        him.setBounds((int) other.getxPos(),(int) other.getyPos(),other.getImage().getWidth(),other.getImage().getHeight());

        return me.intersects(him);
    }

    /**
     * Notification that this entity collided with another.
     *
     * @param other The entity with which this entity collided.
     */
    public abstract void collidedWith(SpriteBase other);

}

