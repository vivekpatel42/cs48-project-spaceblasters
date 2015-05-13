package cs48.project.game.Space_Blasters;

public class Enemy extends SpriteBase {

	private int lvl;
	private int hp;
	private int movement;
	private double Xdirection=1;
	private double Ydirection=0;
	
	public Enemy() {
		super();
		lvl = 1;
		hp = lvl*100;
		movement = 1;
		GetSprite("AngryFace");
	}
	public Enemy(double xDelta, double yDelta){
		super(xDelta,yDelta);
		lvl = 1;
		hp = lvl*100;
		movement = 1;
		GetSprite("AngryFace");
	}
	//Stub
	public void CalculateMove() {
		if (this.movement == 1) {
			move(Xdirection, Ydirection);
			if (this.getXPos() <50 ) {
				Xdirection = 1;
			}
			else if( this.getXPos() >750)
				Xdirection =-1;
		}
		
		
	}

}
