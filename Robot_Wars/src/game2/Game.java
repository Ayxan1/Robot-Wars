package game2;

import audio.MusicPlayer;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    public static int WIDTH, HEIGHT;
    private boolean running = false;
    private Thread thread;
    private Thread threadMusic;
    Handler handler;
    Camera cam;
    static Texture tex;
    
    
     MusicPlayer player = null;
    
    
    
    int nextLevelActive = 0;
    int enemyLimit = 3;
     int counter1 = 0;
     
    
     Player leaderPlayer = null;
     
    float enemyCoordinates[] = new float[3000];

    private BufferedImage level = null, clouds = null;

    private void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();

        BufferedImageLoader loader = new BufferedImageLoader();
        
        if (nextLevelActive == 1) {
            level = loader.loadImage("/level2.png");
        clouds = loader.loadImage("/backGrnd.jpg");

        }else{
         level = loader.loadImage("/level.png");
        clouds = loader.loadImage("/clouds.jpg");
   
        }
        
        handler = new Handler();
        cam = new Camera(0, 0);

        loadImageLevel(level);

       // handler.addObject(new Player(100,100,handler,ObjectId.Player));
        // handler.createLevel();
        this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() {
      
        
        if (running) {
            return;
        }

        running = true;
        
        
        player = new MusicPlayer("bone");      
        thread = new Thread(this);
        threadMusic = new Thread(player);
        thread.start();
        threadMusic.start();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ammountOfTicks = 60.0;
        double ns = 1000000000 / ammountOfTicks;
        double delta = 0;

        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }
            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                System.out.println("FPS :" + frames);
                frames = 0;
            }
        }
        //   stop();
    }

   
    float playerX = 0, playerY = 0;

    public void tick() {
        handler.tick();
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                cam.tick(handler.object.get(i));
                GameObject tempObj = handler.object.get(i);
                
                if (handler.object.get(i).nextLevel == 1) {
                    nextLevelActive = 1;
                    Enemy.enemyCounter = 0;
                    enemyLimit = 10;
                    init();
                    counter1 = 0;
                    Team.currentEnemyX = 0;
                    Team.currentEnemyY = 0;
                                         
                }
           
                
                if (nextLevelActive == 1 ) {
                    
                    handler.object.get(i).changeThema = 1;
                }
                
                
                
                if (handler.object.get(i).autoPlayer == 0) {
                    playerX = handler.object.get(i).x;
                    playerY = handler.object.get(i).y;
                }
            }

            if (handler.object.get(i).getId() == ObjectId.Enemy) {
                if (handler.object.get(i).health <= 0) {
                    
                    /*
                    if (((playerX - handler.object.get(i).x) > 400 || (handler.object.get(i).x - playerX) > 400)) {
                        handler.object.remove(handler.object.get(i));
                        Enemy.enemyCounter--;
                       
                    }
                    */
                      handler.object.remove(handler.object.get(i));
                        Enemy.enemyCounter--;
                      
                    
                    Team.currentEnemyX = 0;
                    Team.currentEnemyY = -1;
                }
            }

            if (Enemy.enemyCounter < enemyLimit && counter1<enemyCoordinates.length && enemyCoordinates[counter1]!= 0) {
                
                handler.addObject(new Enemy(enemyCoordinates[counter1], enemyCoordinates[counter1 + 1], handler, ObjectId.Enemy, 1), 0);
                counter1 += 2;
               
                         
            }
            
            

        }

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        
        
        if (nextLevelActive == 1) {
            g.drawImage(clouds, 0, 0, this);
         g.drawImage(clouds, 512, 0, this);
         g.drawImage(clouds, 0, 289, this);
         g.drawImage(clouds, 512, 289, this);
         g.drawImage(clouds, 0, 289*2, this);
         g.drawImage(clouds, 512, 289*2, this);
        }
        
        
        
        if (nextLevelActive == 0) {
            g.drawImage(clouds, 0, 0, this);
        g.drawImage(clouds, 626, 0, this);
        g.drawImage(clouds, 0, 391, this);
        g.drawImage(clouds, 626, 391, this);
        }
        

        g2d.translate(cam.getX(), cam.getY()); //begin of cam

        /*
         for (int xx = 0; xx < clouds.getWidth()*5; xx += clouds.getWidth()) {
         g.drawImage(clouds, xx, 50, this);
         }
         */
        handler.render(g);

        g2d.translate(-cam.getX(), -cam.getY()); // end of cam

        g.dispose();
        bs.show();
    }

    private void loadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        System.out.println(w + "  " + h);

        int counter = 0;

        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 255 & blue == 255) {
                    handler.addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block), 0);
                }

                if (red == 0 && green == 0 & blue == 255) {
                    leaderPlayer = new Player(xx * 32, yy * 32, handler, ObjectId.Player, 0, -1);
                    handler.addObject(leaderPlayer, 0);
                    
                    handler.addObject(new Player(xx *42, yy * 32, handler, ObjectId.Player, 1, -1), 0);

                }

                if (red == 0 && green == 255 & blue == 0) {

                    enemyCoordinates[counter] = xx * 32;
                    enemyCoordinates[counter + 1] = yy * 32;

                    counter += 2;
                }

                
               
                if (red == 255 && green == 0 & blue == 0) {
                 
                    handler.addObject(new MovingBlock(xx * 32, yy * 32, 0, ObjectId.MovingBlock), 0);
                }
                     
                
                
                 if (red == 255 && green == 128 & blue == 64) {
                 
                    handler.addObject(new MovingBlock1(xx * 32, yy * 32, 0, ObjectId.MovingBlock1), 0);
                }
                   
                
                
                
                
                
                if (red == 0 && green == 255 & blue == 255) {
                    handler.addObject(new UnvisibleBlock(xx * 32, yy * 32, 0, ObjectId.UnvisibleBlock), 0);
                }
                
                
                
                if (red == 155 && green == 50 & blue == 20) {
                 
                    handler.addObject(new FinalBlock(xx * 32, yy * 32, 0, ObjectId.FinalBlock), 0);
                }
                
                
                if (red == 255 && green == 255 & blue == 0) {
                 handler.addObject(new Team(xx * 32, yy * 32, handler, ObjectId.Team, 1), 0);
                    
                }
                

            }
        }

    }

    public static Texture getInstance() {
        return tex;
    }

    public static void main(String[] args) {
        new Window(800, 600, "Nean PLatfORM Game", new Game());
        
    }
}
