/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author ayxan
 */
public class Bullet extends GameObject{

    Handler handler = null;
    
    
    public Bullet(Handler handler, float x, float y, ObjectId id, int velX) {
        super(x, y, id);
        this.velX = velX;
        this.handler = handler;
    }

        
    @Override
    public void tick(LinkedList<GameObject> object) {
       x+=velX;
       this.distance += velX;
       
       // cause decreasing of fps
      // this.Collision(object);
       
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 4, 4);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 4,4);
    }
    

    
     private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Block) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.bulletObject.remove(this);
                }
                
            }
        }
    }



}
