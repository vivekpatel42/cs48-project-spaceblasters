package cs48.project.game.Space_Blasters;


import java.util.ArrayList;

public class ResourceManager {

    private Player mainPlayer;
    private ArrayList<Enemy> enemyArr;
    private ArrayList<Projectile> projectileArr;

    public ArrayList<Projectile> getProjectileArr() {
        return projectileArr;
    }

    public Player getMainPlayer() {
        return mainPlayer;
    }

    public ArrayList<Enemy> getEnemyArr() {
        return enemyArr;
    }

    public ResourceManager() {
        mainPlayer = new Player(400, 400);
        enemyArr = new ArrayList<Enemy>();
        projectileArr = new ArrayList<Projectile>();
    }
    public void Collisions(){
        ArrayList<Enemy> toDelete = new ArrayList<Enemy>(); //COLLISION DETECTION FOR FRIENDLY PROJECTILES TO ENEMIES
        ArrayList<Projectile> toDeleteShot = new ArrayList<Projectile>(); //COLLISION DETECTION FOR FRIENDLY PROJECTILES TO ENEMIES
        for (int i = 0;i< this.getProjectileArr().size(); i++){
            for (int j = 0; j< this.getEnemyArr().size(); j++){
                if (this.getProjectileArr().get(i).collidesWith(this.getEnemyArr().get(j)) && this.getProjectileArr().get(i).isFriendly()) {
                    toDelete.add(this.getEnemyArr().get(j));
                    toDeleteShot.add(this.getProjectileArr().get(i));
                }
            }}
        for (int i = 0; i<toDelete.size(); i++){
            this.getEnemyArr().remove(toDelete.get(i));
            this.getMainPlayer().increaseScore(1000);
        } //END FIRST COLLISION SET
        //COLLISION FOR PLAYER
        for (int i = 0;i< this.getProjectileArr().size(); i++){
                if (this.getProjectileArr().get(i).collidesWith(this.getMainPlayer()) && !(this.getProjectileArr().get(i).isFriendly())) {
                    this.getMainPlayer().GotShot();
                    toDeleteShot.add(this.getProjectileArr().get(i));
                }}
        for (int i = 0; i<toDeleteShot.size(); i++) {
            this.getProjectileArr().remove(toDeleteShot.get(i));
        }
    }

    public void CleanBullets (){
        ArrayList<Projectile> toDeleteShot = new ArrayList<Projectile>(); //COLLISION DETECTION FOR FRIENDLY PROJECTILES TO ENEMIES
        for (int i = 0;i< this.getProjectileArr().size(); i++){
            if (this.getProjectileArr().get(i).getYPos() < 0 || this.getProjectileArr().get(i).getYPos() > 800) {
                toDeleteShot.add(this.getProjectileArr().get(i));
                }
            }
        for (int i = 0; i<toDeleteShot.size(); i++) {
            this.getEnemyArr().remove(toDeleteShot.get(i));
        }
    }
    public void GenerateEnemies() {
        for (int i = 0; i <= 5; i++) {
            Enemy enemy = new Enemy(100 * i - 600, 50 * i);
            enemyArr.add(enemy);
        }

    }


    public boolean checkForGameOver() {
        if(mainPlayer.getHp() <= 0) {
            return true;
        }
        return false;
    }
}
