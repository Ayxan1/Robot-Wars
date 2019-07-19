package game2;

import static game2.ObjectId.Bullet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    
    
    
    float previousTeamCoordinateX = 0;
    
    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            
            
            if (tempObject.getId() == ObjectId.Team) {
                if (tempObject.health > 0) {
                    
                    
                    if (key == KeyEvent.VK_5) {
                        tempObject.activeTracking = false;
                        previousTeamCoordinateX = tempObject.x;
                        tempObject.setVelX(5);
                    }
                    
               
                    
                    
                    if (key == KeyEvent.VK_4) {
                    tempObject.activeTracking = true;
                    }
                    
                    
                    if (tempObject.x - previousTeamCoordinateX >= 400) {
                       tempObject.setVelX(0);
                       previousTeamCoordinateX = 0;
                       tempObject.activeTracking = true;
                    }
                    
                    
                }
            }
            
            
            
            
            if (tempObject.getId() == ObjectId.Player) {
                if (tempObject.health > 0 && tempObject.autoPlayer == 0) {

                    if (key == KeyEvent.VK_RIGHT) {
                        tempObject.setVelX(5);
                        tempObject.side = 1;
                    }
                    if (key == KeyEvent.VK_LEFT) {
                        tempObject.setVelX(-5);
                        tempObject.side = 3;
                    }
                    if (key == KeyEvent.VK_UP) {
                        int x = 1;
                        if (tempObject.side == 3) {
                            x = -1;
                            handler.addObject(new PlayerBullet(this.handler, tempObject.getX() - 15, tempObject.getY() + 35, ObjectId.Bullet, x * 5), 2);
                        }

                        if (tempObject.side == 1) {
                            x = 1;
                            handler.addObject(new PlayerBullet(this.handler, tempObject.getX() + 75, tempObject.getY() + 35, ObjectId.Bullet, x * 5), 2);
                        }

                        tempObject.gunActivation = true;
                    }
                    if (key == KeyEvent.VK_0) {
                        tempObject.health = 0;
                    }

                    if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
                        tempObject.setJumping(true);
                        tempObject.setVelY(-15);
                    }
                }else if(tempObject.health > 0 && tempObject.autoPlayer == 1){
                    if (tempObject.health > 0) {

                    if (key == KeyEvent.VK_D) {
                        tempObject.setVelX(5);
                        tempObject.side = 1;
                    }
                    if (key == KeyEvent.VK_A) {
                        tempObject.setVelX(-5);
                        tempObject.side = 3;
                    }
                    if (key == KeyEvent.VK_W) {
                        int x = 1;
                        if (tempObject.side == 3) {
                            x = -1;
                             handler.addObject(new PlayerBullet(this.handler, tempObject.getX() - 15, tempObject.getY() + 35, ObjectId.Bullet, x * 10), 2);
                        }

                        if (tempObject.side == 1) {
                            x = 1;
                           handler.addObject(new PlayerBullet(this.handler, tempObject.getX() + 75, tempObject.getY() + 35, ObjectId.Bullet, x * 10), 2);
                        }

                        tempObject.gunActivation = true;
                    }
                    if (key == KeyEvent.VK_0) {
                        tempObject.health = 0;
                    }

                    if (key == KeyEvent.VK_2 && !tempObject.isJumping()) {
                        tempObject.setJumping(true);
                        tempObject.setVelY(-15);
                    }
                }
                }
                
                
                
            }

            
            
            
            
            
            
            
            
            
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Player && tempObject.autoPlayer == 0) {
                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_UP) {
                    tempObject.gunActivation = false;
                }
                //if(key == KeyEvent.VK_SPACE) tempObject.setVelY(-10);

            }else if (tempObject.getId() == ObjectId.Player && tempObject.autoPlayer == 1){
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_W) {
                    tempObject.gunActivation = false;
                }
            }
        }
    }
}
