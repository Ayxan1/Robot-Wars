package game2;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> bulletObject = new LinkedList<GameObject>();
    public LinkedList<GameObject> playerBulletObject = new LinkedList<GameObject>();


    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    
    
    private GameObject tempObject;
    float playerX=0, playerY = 0;
    float playerHealthForEnemy = 10;
   
    public void tick() {
     
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
           
            
            if (tempObject.isEnemy == -1 && tempObject.autoPlayer == 0) {
                playerHealthForEnemy = tempObject.health;
                playerX = tempObject.x;
                playerY = tempObject.y;
                Player.leaderPlayerX = playerX;
                Player.leaderPlayerY = playerY;
                
            }
            
            
            if (tempObject.isEnemy == 1) {
                tempObject.playerHealthForEnemy = playerHealthForEnemy;
                tempObject.playerX = playerX;
                tempObject.playerY = playerY;
            }
             
           
            
            tempObject.tick(object);
            
        }

        for (int i = 0; i < bulletObject.size(); i++) {
            tempObject = bulletObject.get(i);
            tempObject.tick(object);
            
            if (this.bulletObject.size() > 0) {
             if (this.bulletObject.get(0).distance > 700 || this.bulletObject.get(0).distance < -700) {
                this.bulletObject.removeAll(this.bulletObject);
            }   
            }
            
        }
        
        
        for (int i = 0; i < playerBulletObject.size(); i++) {
            tempObject = playerBulletObject.get(i);
            tempObject.tick(object);
            
            if (this.playerBulletObject.size() > 0) {
             if (this.playerBulletObject.get(0).distance > 700 || this.playerBulletObject.get(0).distance < -700) {
                this.playerBulletObject.removeAll(this.playerBulletObject);
            }   
            }
            
        }
        
        
        
        
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            tempObject.render(g);

        }
        
        
        
        for (int i = 0; i < bulletObject.size(); i++) {
            tempObject = bulletObject.get(i);
            tempObject.render(g);
        }
        
        
        
        for (int i = 0; i < playerBulletObject.size(); i++) {
            tempObject = playerBulletObject.get(i);
            tempObject.render(g);
        }


    }

    
    
    
    
    public void addObject(GameObject object, int location) {
        if (location == 0) {
            this.object.add(object);
        }
        
        // Enemy bullet
        if (location == 1) {
            if (this.bulletObject.size() < 10) {

                this.bulletObject.add(object);
            }

        }
        
        

        // Player bullet
        if (location == 2) {
            if (this.playerBulletObject.size() < 10) {

                this.playerBulletObject.add(object);
            }

        }
        
        

    }

    public void removeObject(GameObject object, int location) {
        if (location == 0) {
            this.object.remove(object);
        }
        
        if (location == 1) {
            this.bulletObject.remove(object);
        }
        
        if (location == 2) {
            this.playerBulletObject.remove(object);
        }
    }

}
