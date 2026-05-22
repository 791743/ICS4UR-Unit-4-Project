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

abstract class Enemy extends GameObject {
    
    protected int speedX, speedY;

    public Enemy(int x, int y, int speedX, int speedY, String image, Color color){

        super(x, y, 25, 25, image, color);

        this.speedX = speedX;
        this.speedY = speedY;

    }
}