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
public class MovingBlock1 extends GameObject {

    Texture tex = Game.getInstance();
    private int type;
    private GameObject tempObject;
    public float initialPoint = 0; 
    
    
    
    public MovingBlock1 (float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
        this.velY = -4;
        
        this.initialPoint = y;

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (y - this.initialPoint == 700) {
            this.velY =-this.velY;
        }
        if (y - this.initialPoint == 0) {
            this.velY = -1 * this.velY;
        }
        
        this.y += this.velY;
        
        
        if (this.collisionWithPlayer == 1) {

            this.player.y += this.velY;
        }
    }

    @Override
    public void render(Graphics g) {

        //g.drawImage(tex.block[1], (int) x, (int) y, null);
        g.setColor(Color.green);

        g.fillRect((int) this.x, (int) this.y, 300, 40);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, 300, 40);
    }

}
