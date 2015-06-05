package test.cs48.project.game.Space_Blasters; 

import cs48.project.game.Space_Blasters.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** 
* ResourceManager Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 5, 2015</pre> 
* @version 1.0 
*/ 
public class ResourceManagerTest {

    //Player p1 = new Player(0,0);
    //private Player mainPlayer;
    private ResourceManager manager;
    /**
     * Method: GenerateEnemies()
     */
    @Test
    public void testGenerateEnemies() throws Exception {
        manager = new ResourceManager();
        manager.GenerateEnemies();
        boolean expectedBoolean = true;
        ArrayList<Enemy> expectedEnemyArr = new ArrayList<Enemy>();
        for (int i = 0; i <= 5; i++) {
            Enemy enemy = new Enemy(100 * i - 600, 50 * i);
            expectedEnemyArr.add(enemy);
        }
        if(manager.getEnemyArr() == expectedEnemyArr)
            expectedBoolean = false;
        assertTrue(expectedBoolean);
    }

    /**
     * Method: GenerateEnemies(int version)
     */
    @Test
    public void testGenerateEnemiesVersion() throws Exception {
        manager = new ResourceManager();
        manager.GenerateEnemies(1);
        boolean expectedBoolean = true;
        ArrayList<Enemy> expectedEnemyArr = new ArrayList<Enemy>();
        for (int i = 0; i <= 5; i++) {
            Enemy enemy = new Enemy(100 * i - 600, 50 * i);
            expectedEnemyArr.add(enemy);
        }
        if(manager.getEnemyArr() == expectedEnemyArr)
            expectedBoolean = false;
        assertTrue(expectedBoolean);
    }

    /**
     * Method: checkForGameOver()
     */
    @Test
    public void testCheckForGameOver1() throws Exception {
        //mainPlayer.setHp(50);
        manager = new ResourceManager();
        Player player1 =manager.getMainPlayer();
        player1.setHp(50);
        assertFalse(manager.checkForGameOver());
    }
    @Test
    public void testCheckForGameOver2() throws Exception {
        manager = new ResourceManager();
        Player player2 = manager.getMainPlayer();
        player2.setHp(-100);
        assertFalse(manager.checkForGameOver());
    }
}
