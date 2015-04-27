package cs48.project.game.Space_Blasters;


import java.util.ArrayList;

public class ResourceManager {

	private Player You;
	private ArrayList<Enemy> enemyArr;
	private ArrayList<Projectile> projectileArr;

	
	public ResourceManager() {
		You = new Player();
		enemyArr = new ArrayList<Enemy>();
		projectileArr = new ArrayList<Projectile>();
	}
	
	

}
