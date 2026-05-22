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

class PatrolEnemy extends Enemy{
    public PatrolEnemy(int x, int y){
        super(x,y,4,4,"enemy3.png",new Color(211,84,0));
    }
 //this enemy    
    @Override
    public void move(){
        x+=speedX;
        y+=speedY;
        if(x<0 || x>565){
            speedX *= -1;
        }
        
        if(y<50 || y > 465){
            speedY *= -1;
        }
    }
}