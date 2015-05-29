package test.cs48.project.game.Space_Blasters; 

import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

/** 
* BufferedImageLoader Tester. 
*
 * @author <Jingzhou Xue>
 * @since <pre>05/28/2015</pre>
 * @version 1.0
 */
public class BufferedImageLoaderTest extends TestCase { 
public BufferedImageLoaderTest(String name) { 
super(name); 
} 

public void setUp() throws Exception { 
super.setUp(); 
} 

public void tearDown() throws Exception { 
super.tearDown(); 
} 

/** 
* 
* Method: loadImage(String path) 
* 
*/ 
public void testLoadImage() throws Exception { 
//TODO: Test goes here...
    BufferedImageLoaderTest bt = new BufferedImageLoaderTest("haha");

} 



public static Test suite() { 
return new TestSuite(BufferedImageLoaderTest.class); 
} 
} 
