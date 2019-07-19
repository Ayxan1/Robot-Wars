
package game2;

import com.sun.javafx.iio.ImageLoader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
    
    private BufferedImage image;
    
    public BufferedImage loadImage(String path){
        
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            System.out.println("Sekil yuklenmedi");
            System.exit(1);
        }
        
        return image;

    }
    
}
