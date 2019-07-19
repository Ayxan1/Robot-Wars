
package game2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
    
    protected float x,y;
    protected ObjectId id;
    protected float velX=0, velY=0;
    protected boolean falling = false;
    protected boolean  jumping = false;
    protected boolean gunActivation = false;
    protected float health = 10;
    protected int side = 1;
    protected int distance = 0;
    protected Player player = null;
    protected int collisionWithPlayer = 0;
    
    protected int autoPlayer = 0;
    
    protected int isEnemy = 0;
    
    protected int nextLevel = 0;
    
    protected int changeThema = 0;
    
    protected float playerHealthForEnemy = 10;
    
    
    protected float playerX, playerY = 0;
    
    
    
    protected boolean activeTracking = true;
    
    public GameObject(float x,float y,ObjectId id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
      public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    
    public void setX(float x) {
        this.x = x;
    }

    
    public void setY(float y) {
        this.y= y;
    }

    
    public float getVelX() {
        return velX;
    }

    
    public float getVelY() {
        return velY;
    }

    
    public void setVelX(float velX) {
        this.velX = velX;
    }

    
    public void setVelY(float velY) {
        this.velY = velY;
    }

    
    public ObjectId getId() {
        return id;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
    
}
