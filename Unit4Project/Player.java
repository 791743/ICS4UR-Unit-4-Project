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

class Player extends GameObject {

    private int speed = 5;
    private int coins;
    private BufferedImage Img;


    public Player(int x, int y) {

        super(x, y, 40, 40, "alienfront.png", new Color(41, 128, 185));
    }
//moving without leaving the screen
    public void move(int dx, int dy, int screenWidth, int screenHeight) {
        
       if (x + dx * speed >= 0 && x + dx * speed < screenWidth - width){
           x += dx * speed;
       }
       
       if (y + dy * speed >= 50 && y + dy * speed < screenHeight - height){
           y += dy * speed;
        }
    }
//getter and setter for speed    
    public void setSpeed(int newSpeed){
        this.speed = newSpeed;
    }
    
    public int getSpeed(){
        return this.speed;
    }
}