
package game2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class FinalBlock extends GameObject {
    
    
    Texture tex = Game.getInstance();
    private int type;
    
    public FinalBlock(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
    }

    @Override
    public void render(Graphics g) {
        
        
       
    }

    @Override
    public Rectangle getBounds() {
         return new Rectangle((int) this.x, (int) this.y, 32, 32);
    }

    
}
