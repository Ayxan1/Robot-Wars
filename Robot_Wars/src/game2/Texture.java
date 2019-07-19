/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.image.BufferedImage;

/**
 *
 * @author ayxan
 */
public class Texture {
    SpriteSheet bs, ps, ps_0;
    
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage player_sheet_0 = null;
    
    
    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[60];
    
    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            block_sheet = loader.loadImage("/block_sheet.png");
            player_sheet = loader.loadImage("/player_sheet2.png");
            player_sheet_0 = loader.loadImage("/player_sheet3.png");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        ps_0 = new SpriteSheet(player_sheet_0);
        
        getTextures();
    }
    
    
    private void getTextures(){
        block[0] = bs.grabImage(1, 11, 32, 32); //dirt block
        block[1] = bs.grabImage(6, 1, 34, 34); //dirt block
        
        
        // going right
        player[0] = ps.grabImage(1, 1, 81, 83);
        player[1] = ps.grabImage(2, 1, 81, 83);
        player[2] = ps.grabImage(3, 1, 81, 83);
        player[3] = ps.grabImage(4, 1, 81, 83);
        player[4] = ps.grabImage(5, 1, 81, 83);
        player[5] = ps.grabImage(6, 1, 81, 83);
        player[6] = ps.grabImage(7, 1, 81, 83);
        player[7] = ps.grabImage(1, 2, 81, 83);
        player[8] = ps.grabImage(2, 2, 81, 83);
        player[9] = ps.grabImage(3, 2, 81, 83);
        
       
        
        // going left
        player[30] = ps_0.grabImage(7, 1, 81, 83);
        player[31] = ps_0.grabImage(6, 1, 81, 83);
        player[32] = ps_0.grabImage(5, 1, 81, 83);
        player[33] = ps_0.grabImage(4, 1, 81, 83);
        player[34] = ps_0.grabImage(3, 1, 81, 83);
        player[35] = ps_0.grabImage(2, 1, 81, 83);
        player[36] = ps_0.grabImage(1, 1, 81, 83);
        player[37] = ps_0.grabImage(7, 2, 81, 83);
        player[38] = ps_0.grabImage(6, 2, 81, 83);
        player[39] = ps_0.grabImage(5, 2, 81, 83);

        
       
        
       
        // Gun animation 
         for (int i = 10; i < 17; i++) {
            player[i] = ps.grabImage((i-9), 3, 81, 83);
        }
         
        for (int i = 17; i < 21; i++) {
            player[i] = ps.grabImage((i-16), 4, 81, 83);
        }
        
        
        
        // Gun animation Left side
        for (int i = 40, j=7; i < 47 ;j--, i++) {
            player[i] = ps_0.grabImage(j , 3, 81, 83);

        }
        
        for (int i = 47, j=7; i < 51;j--, i++) {
            player[i] = ps_0.grabImage(j, 4, 81, 83);
        }
        
        
        
        
        
        // Death animation Right
        player[21] = ps.grabImage(6, 4, 81, 83);
        player[22] = ps.grabImage(7, 4, 81, 83);
        for (int i = 23; i < 30; i++) {
            player[i] = ps.grabImage((i-22), 5, 81, 83);
        }
        
        
        
        // Death animation Left
        player[51] = ps_0.grabImage(2, 4, 81, 83);
        player[52] = ps_0.grabImage(1, 4, 81, 83);
        for (int i = 53, j=7; i < 60; j--, i++) {
            player[i] = ps_0.grabImage(j, 5, 81, 83);
        }
        
        
        
        
        
       /*
        player[2] = ps.grabImage(1, 1, 32, 46);
        player[3] = ps.grabImage(1, 1, 32, 46);
        player[0] = ps.grabImage(1, 1, 32, 46);
        player[0] = ps.grabImage(1, 1, 32, 46);
       */
        
    }
    
}
