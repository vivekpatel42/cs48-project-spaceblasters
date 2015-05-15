import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
/*
* @author Gabriel Romero
* Basic BufferedImage loader sampled from Stack Overflow
*
*/
public class BufferedImageLoader {
   
    private BufferedImage image;
    
    public BufferedImage loadImage(String path) throws IOException {
	image = ImageIO.read(getClass().getResource(path));
	return image;
    } 
}