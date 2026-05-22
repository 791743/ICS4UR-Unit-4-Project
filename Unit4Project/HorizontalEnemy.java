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

class HorizontalEnemy extends Enemy{
    public HorizontalEnemy(int x, int y){
        super(x,y,3,0,"enemy1.png", new Color(231,76,60));
    }
    
    @Override
    public void move(){
        x+= speedX;
        if(x<0||x>560){
            speedX *= -1;
            
        }
    }
}