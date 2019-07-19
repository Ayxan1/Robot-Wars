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
public class MovingBlock extends GameObject {

    Texture tex = Game.getInstance();
    private int type;
    private GameObject tempObject;
    public float initialPoint = 0; 
    
    
    
    public MovingBlock(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
        this.velX = -4;
        
        this.initialPoint = x;

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (x - this.initialPoint == 700) {
            this.velX =-this.velX;
        }
        if (x - this.initialPoint == 0) {
            this.velX = -1 * this.velX;
        }
        
        this.x += this.velX;
        
        
        if (this.collisionWithPlayer == 1) {

            this.player.x += this.velX;
        }
        
    }

    @Override
    public void render(Graphics g) {

        //g.drawImage(tex.block[1], (int) x, (int) y, null);
        g.setColor(Color.red);

        g.fillRect((int) this.x, (int) this.y, 200, 40);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, 200, 40);
    }

}
