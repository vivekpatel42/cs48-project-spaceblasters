package com.cs48.projects.games.space_blasters;

public class Enemy extends SpriteBase {

	private int lvl;
	private int hp;
	private int movement;
	private double dx;
	private double dy;
	
	public Enemy() {
		lvl = 1;
		hp = lvl*100;
		movement = 1;
		dx = 1;
		dy = 0;
	}
	public Enemy(double xDelta, double yDelta){
		lvl = 1;
		hp = lvl*100;
		movement = 1;
		dx = xDelta;
		dy = yDelta;
	
	}
	//Stub
	public void move() {
		if (this.movement == 1) {
			move(dx, dy);
			if ((xPos - (image.getWidth()/2)) == 0 || (xPos + (image.getWidth()/2)) == 800) {
				dx *= -1;
			}
		}
		else if (this.movement == 2) {
		
		
		}
		else if (this.movement == 3) {
		
		
		
		}
		
		
		
	}

}
