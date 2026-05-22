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

class Coin extends GameObject {

    public Coin(int x, int y) {

        super(x, y, 20, 20, "coin.png", new Color(241, 196, 15));
    }
}