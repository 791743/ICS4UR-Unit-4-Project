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

abstract class GameObject {
//protected so only the player and enemy classes can use
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected Color color;

//constructor
    public GameObject(int x, int y, int width, int height, String imageName, Color color) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        //try to load the image if it doesnt work, use a box
        try{
            this.image = new ImageIcon(imageName).getImage();
        }catch(Exception e){
            this.image = null;
        }
    }
//drawing object to screen
    public void draw(Graphics g){
        if(image != null && image.getWidth(null) > 0){
            g.drawImage(image, x, y, width, height, null);
        }else{
            g.setColor(color); //fallback colour and shape if it does not work
            g.fillRect(x, y, width, height);
        }
    }
    //Empty so that subclasses like player and enemy can override
    public void move() {
        
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}