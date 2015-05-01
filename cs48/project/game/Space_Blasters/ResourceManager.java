package cs48.project.game.Space_Blasters;


import java.util.ArrayList;

public class ResourceManager {

	private Player You;
	private ArrayList<Enemy> enemyArr;
	private ArrayList<Projectile> projectileArr;

	public ArrayList<Projectile> getProjectileArr() {
		return projectileArr;
	}
	public Player getYou() {
		return You;
	}
	public ArrayList<Enemy> getEnemyArr() {
		return enemyArr;
	}

	public ResourceManager() {
		You = new Player(400, 400);
		enemyArr = new ArrayList<Enemy>();
		projectileArr = new ArrayList<Projectile>();
	}
	public void GenerateEnemies (){
		for ( int i = 0; i <= 6; i++){
			Enemy enemy = new Enemy(70*i, 50*i);
			enemyArr.add(enemy);
		}

	}
	

}
