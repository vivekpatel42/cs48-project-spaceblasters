package src.cs48.project.game.Space_Blasters;


public class Player extends SpriteBase {
    public Player(double xPos, double yPos) {
        super(xPos, yPos);
        GetSprite("Player");
    }

    public Player() {
        super();
        GetSprite("Player");
    }

    @Override
    public void collidedWith(SpriteBase other) {

    }
}
