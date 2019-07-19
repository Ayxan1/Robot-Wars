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
public class UnvisibleBlock extends GameObject {

    Texture tex = Game.getInstance();
    private int type;
    private GameObject tempObject;

    public UnvisibleBlock(float x, float y, int type, ObjectId id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {

        //g.drawImage(tex.block[1], (int) x, (int) y, null);
        
        /*
        g.setColor(Color.GREEN);

        g.fillRect((int) this.x, (int) this.y, 32, 32);
                */
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, 32, 32);
    }

}
