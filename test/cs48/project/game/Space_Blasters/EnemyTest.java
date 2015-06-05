package test.cs48.project.game.Space_Blasters;

import cs48.project.game.Space_Blasters.Enemy;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Enemy Tester.
 *
 * @author <Jingzhou Xue>
 * @since <pre>May.29th 2015</pre>
 * @version 1.0
 */
public class EnemyTest {
    Enemy enemy;

    /**
     * Method: CalculateMove()
     */
    @Test
    public void testCalculateMove1() throws Exception {
        enemy = new Enemy(400,400,1);
        double Ydirection = 1.0;
        assertEquals(enemy.getYdirection(),Ydirection,0.1);

    }
    @Test
    public void testCalculateMove2() throws Exception {
        enemy = new Enemy(400,400,2);
        double Xdirection = 1.0;
        enemy.setxPos(25);
        enemy.CalculateMove();

        assertEquals(enemy.getXdirection(), Xdirection, 0.1);

    }
    @Test
    public void testCalculateMove3() throws Exception {
        enemy = new Enemy(400,400,3);
        double Ydirection = 1.0;
        enemy.setyPos(775);
        enemy.setxPos(775);
        enemy.CalculateMove();

        assertEquals(enemy.getYdirection(),Ydirection,0.1);

    }

}
