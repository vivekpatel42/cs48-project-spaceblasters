package cs48.project.game.Space_Blasters;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected String type;

    /** The rectangle used for this entity during collisions  resolution */
    private Rectangle me = new Rectangle();
    /** The rectangle used for other entities during collision resolution */
    private Rectangle him = new Rectangle();

    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(int i){speed = i;}
    public double getYPos() {
        return yPos;
    }
    public void setyPos(double yPos) { yPos=this.yPos;}
    public double getXPos() {
        return xPos;
    }
    public void setxPos(double xPos) { xPos=this.xPos;}


    /**
     * Constructor to determine initial position
     * @param xPos Initial Xposition
     * @param yPos Initial Yposition
     */
    public SpriteBase(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = 5.0;
        this.image = null;
        this.type ="";
    }

    public SpriteBase() {
        this.xPos = 0;
        this.yPos = 0;
        this.speed = 5.0;
        this.image = null;
        this.type="";
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
     *
     * @param other the other sprite
     * @return true if collision is detected collision is determined by the abstract method collidedWith
     */
    public boolean collidesWith(SpriteBase other) {
        me.setBounds((int) xPos, (int) yPos, this.getImage().getWidth(), this.getImage().getHeight());
        him.setBounds((int) other.getXPos(),(int) other.getYPos(),other.getImage().getWidth(),other.getImage().getHeight());

        return me.intersects(him);
    }

}

