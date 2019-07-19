package game2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 70, height = 82;
    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;
    
    private Handler handler;
    
    
    public static float leaderPlayerX = 0;
    public static float leaderPlayerY = 0;
    
    
    
    
    Texture tex = Game.getInstance();

    private Animation playerWalkR;
    private Animation playerWalkL;
    private Animation playerShoot;
    private Animation playerShootL;
    private Animation playerDeath;
    private Animation playerDeathL;

    public Player(float x, float y, Handler handler, ObjectId id, int automation, int isEnemy) {
        super(x, y, id);
        this.handler = handler;
        this.isEnemy = isEnemy;

        playerWalkR = new Animation(10, -1, tex.player[0], tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6], tex.player[7]);
        playerShoot = new Animation(15, -1, tex.player[9], tex.player[10], tex.player[11], tex.player[12], tex.player[13], tex.player[14], tex.player[15], tex.player[16], tex.player[17], tex.player[18], tex.player[19], tex.player[20]);
        playerShootL = new Animation(15, -1, tex.player[39], tex.player[40], tex.player[41], tex.player[42], tex.player[43], tex.player[44], tex.player[45], tex.player[46], tex.player[47], tex.player[48], tex.player[49], tex.player[50]);
        playerDeath = new Animation(15, 0, tex.player[21], tex.player[22], tex.player[23], tex.player[24], tex.player[25], tex.player[26], tex.player[27], tex.player[28], tex.player[29]);
        playerDeathL = new Animation(15, 1, tex.player[51], tex.player[52], tex.player[53], tex.player[54], tex.player[55], tex.player[56], tex.player[57], tex.player[58], tex.player[59]);
        playerWalkL = new Animation(10, -1, tex.player[30], tex.player[31], tex.player[32], tex.player[33], tex.player[34], tex.player[35], tex.player[36], tex.player[37]);

        this.collisionWithPlayer = 1;
        this.autoPlayer = automation;
        
        this.health = 1000;
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
        
        
        if (this.y > 2400) {
            falling = false;
                    jumping = false;
                    this.health = 0;
            
        }

        Collision(object);

    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.FinalBlock) {
                if (getBounds().intersects(tempObject.getBounds())) {

                    this.nextLevel = 1;
                }
            }

            
            
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
                    x = tempObject.getX() - width;
                }
                //Left
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + width / 2;
                }
            }
            
            
            
            if (tempObject.getId() == ObjectId.Enemy) {

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
                    x = tempObject.getX() - width/2;
                }
                //Left
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + width / 2;
                }
            }

            
            
            
            if (tempObject.getId() == ObjectId.MovingBlock) {
                tempObject.player = this;

                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 32;
                    velY = 0;
                    this.health = 0;
                }

                if (getBounds().intersects(tempObject.getBounds())) {

                    y = tempObject.getY() - height;
                    velY = 0;

                    falling = false;
                    jumping = false;
                    this.health = 0;
                } else {

                    falling = true;

                }

                //Right
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - width;
                    this.health = 0;
                }
                //Left
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + width / 2;
                    this.health = 0;
                }
            }
            
            
            
             if (tempObject.getId() == ObjectId.MovingBlock1) {
                tempObject.player = this;

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

        for (int i = 0; i < handler.bulletObject.size(); i++) {
            GameObject tempObject = handler.bulletObject.get(i);

            if (getBoundsTop().intersects(tempObject.getBounds())) {

                this.health -= 1;
                 handler.bulletObject.remove(tempObject);
            }
            if (getBounds().intersects(tempObject.getBounds())) {

                falling = false;
                jumping = false;

                this.health -= 1;
                 handler.bulletObject.remove(tempObject);
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
        
        
        if (this.changeThema==0) {
            g.setColor(Color.GREEN);
        }else{
            g.setColor(Color.ORANGE);
        }

        
      //  g.fillRect((int) ((int) x + (width / 4)), (int) y-20, ((int) this.health)*(int)width / 10, (int) height / 16);
        
        
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
        g2d.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
        */
        
        

        g2d.setColor(Color.blue);
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

}
