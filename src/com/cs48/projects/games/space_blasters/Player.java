package com.cs48.projects.games.space_blasters;

public class Player extends SpriteBase {


	private int lvl;
	private int hp;
	private int exp;
	
    public Player(double xPos, double yPos) {
        super(xPos, yPos);
        GetSprite("Player");
        type = "Player";
        exp = 0;
        hp = 100;
    }

    public Player() {
        super();
        GetSprite("Player");
        type = "Player";
        exp = 0;
        hp = 100;
    }

    @Override
    public boolean friendlyCollision(SpriteBase other) {
	if (	
    }
}
