package test.cs48.project.game.Space_Blasters; 

import org.junit.Test; 
import static org.junit.Assert.*;
import cs48.project.game.Space_Blasters.*;
/** 
* Player Tester. 
*
 * @author <Jingzhou Xue>
 * @since <pre>May.29th 2015</pre>
 * @version 1.0
 */
public class PlayerTest { 

/** 
* 
* Method: GotShot() 
* 
*/
Player gamer;
@Test
public void testGotShot1() throws Exception {
    gamer = new Player(400,400);
    gamer.GotShot();
    int expectedHP = 90;
    assertEquals(gamer.getHp(),expectedHP,1);
}

@Test
public void testGotShot2() throws Exception {
    gamer = new Player(400,400);
    for(int i=0; i<10;i++) {
        gamer.GotShot();
    }
    assertTrue(gamer.GotShot());
}


/** 
* 
* Method: increaseScore(long score) 
* 
*/ 
@Test
public void testIncreaseScore() throws Exception { 
    gamer = new Player(400,400);
    gamer.increaseScore(10000);
    double expectedScore = 10000;
    assertEquals(gamer.getScore(),expectedScore,1);
} 


} 
