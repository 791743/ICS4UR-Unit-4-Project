import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class ChaserEnemy extends Enemy{
    private Player target;
    
    public ChaserEnemy(int x, int y, Player target){
        super(x,y, 1, 1, "enemy2.png", new Color(142,68,173));
        this.target = target;
    }
  
 @Override  
    public void move(){
        if(x<target.x){
            x += speedX;
        }
        else if(x>target.y){
            x -= speedX;
        }
        
        if(y<target.y){
            y+=speedY;
        }
        else if(y>target.y){
            y-=speedY;
        }
    }
    
}