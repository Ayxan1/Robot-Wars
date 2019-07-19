
package game2;

public class Camera {
    
    private float x,y;
    
    public Camera(float x,float y){
        this.x = x;
        this.y= y;
    }
    
    public void tick(GameObject player){
        //x = -player.getX() + Game.WIDTH/2;
        
        if(player.autoPlayer == 0){
         float xTarg = -player.getX() + Game.WIDTH/2;

        //x += (xTarg-x)*(0.3)﻿;
        //x = -player.getX() + Game.WIDTH/2;
         x += 0.05*(xTarg-x);
         
         
         float yTarg = -player.getY() + Game.HEIGHT/2;

        //x += (xTarg-x)*(0.3)﻿;
        //x = -player.getX() + Game.WIDTH/2;
         y += 0.05*(yTarg-y);
        }
        
    }
    
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
