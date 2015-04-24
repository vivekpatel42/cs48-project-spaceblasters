package com.cs48.projects.games.space_blasters;

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
