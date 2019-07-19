/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 *
 * @author ayxan
 */
public class Team extends GameObject {

    private float width = 70, height = 82;
    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;

    private Handler handler;

    public float previousVelx = 0;

    public static int enemyCounter = 0;

    public static float currentEnemyX = 0;
    public static float currentEnemyY = 0;

    Texture tex = Game.getInstance();

    private Animation playerWalkR;
    private Animation playerWalkL;
    private Animation playerShoot;
    private Animation playerShootL;
    private Animation playerDeath;
    private Animation playerDeathL;

    public Team(float x, float y, Handler handler, ObjectId id, int isEnemy) {
        super(x, y, id);
        this.isEnemy = isEnemy;
        this.handler = handler;

        this.health = 1000;

        playerWalkR = new Animation(10, -1, tex.player[0], tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6], tex.player[7]);
        playerShoot = new Animation(15, -1, tex.player[9], tex.player[10], tex.player[11], tex.player[12], tex.player[13], tex.player[14], tex.player[15], tex.player[16], tex.player[17], tex.player[18], tex.player[19], tex.player[20]);
        playerShootL = new Animation(15, -1, tex.player[39], tex.player[40], tex.player[41], tex.player[42], tex.player[43], tex.player[44], tex.player[45], tex.player[46], tex.player[47], tex.player[48], tex.player[49], tex.player[50]);
        playerDeath = new Animation(15, 0, tex.player[21], tex.player[22], tex.player[23], tex.player[24], tex.player[25], tex.player[26], tex.player[27], tex.player[28], tex.player[29]);
        playerDeathL = new Animation(15, 1, tex.player[51], tex.player[52], tex.player[53], tex.player[54], tex.player[55], tex.player[56], tex.player[57], tex.player[58], tex.player[59]);
        playerWalkL = new Animation(10, -1, tex.player[30], tex.player[31], tex.player[32], tex.player[33], tex.player[34], tex.player[35], tex.player[36], tex.player[37]);

    }

    @Override
    public void tick(LinkedList<GameObject> object) {

        x += velX;
        y += velY;

        if (falling || jumping) {
            velY += gravity;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }

        }

        if (this.health > 0 && this.playerHealthForEnemy > 0) {

            float diffY = currentEnemyY - this.y;
            float diffX = this.currentEnemyX - this.x;
            
                if (this.currentEnemyX < this.x) {
                    diffX *= -1;
                }
                
                if (currentEnemyY < this.y) {
                    diffY *= -1;
                }
            
            
            
                    if(diffX <= 500){
                        if (this.x < currentEnemyX) {
                            
                            handler.addObject(new PlayerBullet(this.handler, this.getX() + 75, this.getY() + 35, ObjectId.Bullet, 10), 2);
                        } else {
                            handler.addObject(new PlayerBullet(this.handler, this.getX() - 15, this.getY() + 35, ObjectId.Bullet, -10), 2);
                        }
                    }
                        
                        

            if ((this.playerX >= this.x) && ((this.playerX - this.x) <= 1500)) {
                

                if (((((diffX) <= 500) && this.currentEnemyX != 0)) && (diffY <= 300 && diffY >= -300)) {

                    if (this.activeTracking == true) {
                        this.setVelX(0);
                    }

                  //  if ((int) this.y == (int) this.currentEnemyY) {
                        this.gunActivation = true;
                        
                       

                  //  }

                } else if ((this.playerX - this.x < 200)) {
                    if (this.activeTracking == true) {
                        this.setVelX(0);
                    }
                } else {
                    if (this.activeTracking == true) {
                        this.setVelX(3);
                    }
                    this.gunActivation = false;
                }
                this.side = 1;
            }

            if ((this.playerX < this.x) && ((this.x - this.playerX) <= 1500)) {

                if (((((diffX) <= 500) && this.currentEnemyX != 0)) && (diffY <= 300 && diffY >= -300)) {
                    
                    if (this.activeTracking == true) {
                        this.setVelX(0);
                    }

                 //   if ((int) this.y == (int) this.currentEnemyY) {
                        this.gunActivation = true;

             
                   // }

                } else if ((this.x - this.playerX < 200)) {
                    if (this.activeTracking == true) {
                        this.setVelX(0);
                    }
                } else {
                    if (this.activeTracking == true) {
                        this.setVelX(-3);
                    }
                    this.gunActivation = false;
                }
                this.side = 3;
            }

        } else {
            this.setVelX(0);
            this.gunActivation = false;

        }

        if (this.y > 2400) {
            falling = false;
            jumping = false;
            this.health = 0;

        }

        this.previousVelx = velX;
        Collision(object);

    }

    private void Collision(LinkedList<GameObject> object) {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Block) {

                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 32;
                    velY = 0;
                }
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }

                //Right
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = this.getX() - width / 2;

                    if (!this.isJumping()) {
                        this.setJumping(true);
                        this.setVelY(-15);
                    } else {
                        this.setJumping(false);
                    }

                }

                //Left
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = this.getX() + width / 2;

                    if (!this.isJumping()) {
                        this.setJumping(true);
                        this.setVelY(-15);
                    } else {
                        this.setJumping(false);
                    }

                }

            }

            if (health > 0) {

                if (tempObject.getId() == ObjectId.UnvisibleBlock) {
                    if (getBoundsJump().intersects(tempObject.getBounds())) {
                        if (!this.isJumping()) {
                            this.setJumping(true);
                            this.setVelY(-15);
                            this.setJumping(false);
                        } else {
                            this.setJumping(false);
                        }
                    }
                }
            }

           // if (tempObject.getId() == ObjectId.Player || tempObject.getId() == ObjectId.PlayerAuto) {
                /*
             if (getBoundsTop().intersects(tempObject.getBounds())) {
             y = this.getY() + 32;
             velY = 0;
             }
             if (getBounds().intersects(tempObject.getBounds())) {
             y = this.getY() - height;
             velY = 0;
             falling = false;
             jumping = false;
             } else {
             falling = true;
             }
                
             */
            /*
             //Right
             if (getBoundsRight().intersects(tempObject.getBounds())) {

             x = this.getX() - width / 2;
             velX = 0;

             }
             //Left
             if (getBoundsLeft().intersects(tempObject.getBounds())) {

             x = this.getX() + width / 2;
             velX = 0;
             }

             }
             */
            
            
            
            
              
        if (tempObject.getId() == ObjectId.MovingBlock1) {
               

                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 32;
                   
                    
                }

                if (getBounds().intersects(tempObject.getBounds())) {
                    
                    y = tempObject.getY() - height;
                    velY = tempObject.velY;
                    falling = false;
                    jumping = false;
                } else {

                    falling = true;

                }

                //Right
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - width;
                }
                //Left
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + width / 2;
                }
            }
        

            
        }

        
        
        
        
        
        
        // player BULLETTT
        for (int i = 0; i < handler.bulletObject.size(); i++) {
            GameObject tempObject = handler.bulletObject.get(i);

            if (getBoundsTop().intersects(tempObject.getBounds())) {
                velY = 0;

                this.health -= 1;
            }
            if (getBounds().intersects(tempObject.getBounds())) {

                velY = 0;
                falling = false;
                jumping = false;

                this.health -= 1;
            } else {
                falling = true;
            }

            //Right
            if (getBoundsRight().intersects(tempObject.getBounds())) {

                this.health -= 1;
                handler.bulletObject.remove(tempObject);
            }

            //Left
            if (getBoundsLeft().intersects(tempObject.getBounds())) {

                this.health -= 1;
                handler.bulletObject.remove(tempObject);
            }

        }
        
        
        
      
        
        
        
        
        
        
        
        
        
        

    }

    int live = 1;
    int dead = 0;

    @Override
    public void render(Graphics g) {

        if (this.changeThema == 0) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }

       // g.fillRect((int) ((int) x + (width / 4)), (int) y - 20, ((int) this.health) * (int) width / 10, (int) height / 16);

        if (velX > 0 && live == 1 && this.jumping == false) {
            playerWalkR.runAnimation();
            playerWalkR.drawAnimation(g, (int) x, (int) y, 81, 83);
        }

        if (velX < 0 && live == 1 && this.jumping == false) {
            playerWalkL.runAnimation();
            playerWalkL.drawAnimation(g, (int) x, (int) y, 81, 83);
        }

        if (this.gunActivation == true && live == 1 && velX == 0) {
            if (this.side == 1) {
                playerShoot.runAnimation();
                playerShoot.drawAnimation(g, (int) x, (int) y, 81, 83);
            }
            if (this.side == 3) {
                playerShootL.runAnimation();
                playerShootL.drawAnimation(g, (int) x, (int) y, 81, 83);
            }

        }

        if ((velX == 0 && live == 1 && this.gunActivation == false) || (this.jumping == true && this.gunActivation == false)) {
            if (this.side == 1) {
                g.drawImage(tex.player[9], (int) x, (int) y, 81, 83, null);
            }
            if (this.side == 3) {
                g.drawImage(tex.player[39], (int) x, (int) y, 81, 83, null);
            }

        }

        if (this.health <= 0) {

            //System.out.println("playerDeath.animationCounterL --- " + playerDeath.animationCounterL);
            live = 0;
            if (playerDeath.animationCounterR == 0) {
                if (this.side == 1) {
                    playerDeath.runAnimation();
                    playerDeath.drawAnimation(g, (int) x, (int) y, 81, 83);

                }

            }

            if (playerDeathL.animationCounterL == 0) {
                if (this.side == 3) {
                    playerDeathL.runAnimation();
                    playerDeathL.drawAnimation(g, (int) x, (int) y, 81, 83);

                }

            }

            if (playerDeathL.animationCounterL > 0 || playerDeath.animationCounterR > 0) {
                if (this.side == 1) {
                    g.drawImage(tex.player[29], (int) x, (int) y, 81, 83, null);
                }
                if (this.side == 3) {
                    g.drawImage(tex.player[59], (int) x, (int) y, 81, 83, null);
                }

            }

        }

        Graphics2D g2d = (Graphics2D) g;
        /*
         g2d.setColor(Color.GREEN);
         g2d.draw(getBounds());
         g2d.draw(getBoundsTop());
         g2d.draw(getBoundsLeft());
         g2d.draw(getBoundsRight());
         g2d.setColor(Color.ORANGE);
         g2d.draw(getBoundsJump());
         
        
         */

        //g2d.draw(getBoundsHealth());
        
        
        
        g2d.setColor(Color.yellow);
         g2d.draw(getBounds());
         g2d.draw(getBoundsTop());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + height / 2), (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsTop() {

        return new Rectangle((int) ((int) x + (width / 4)), (int) y, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
    }

    public Rectangle getBoundsHealth() {

        return new Rectangle((int) ((int) x + (width / 4)), (int) y - 20, ((int) this.health) * (int) width / 10, (int) height / 16);
    }

    public Rectangle getBoundsJump() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + height), (int) width, (int) height / 8);

    }
}
