/*
 * ICS4UR Final Project Example
 * Java Swing Game
 *
 * FEATURES:
 * - Player movement
 * - Randomized coins
 * - Bad objects/enemies
 * - Coin shop boosts
 * - 3 increasing difficulty levels
 * - Layout managers
 * - User interaction
 * - Event handling
 * - File I/O (high score save/load)
 * - Collision detection
 * - OOP with abstract classes/inheritance
 *
 * HOW TO RUN:
 * 1. Save as GameMain.java
 * 2. Compile: javac GameMain.java
 * 3. Run: java GameMain
 */

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

public class GameMain extends JFrame {
//window setup
    public GameMain() {
        setTitle("ICS4UR Coin Dodge Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null); // centers window
        //cardlayout helps to swap between screens
        CardLayout cardLayout = new CardLayout();
        //the panel that holds the screens
        JPanel container = new JPanel(cardLayout);
        //initialize the main menu and give it the panel to allow the switch
        MenuPanel menu = new MenuPanel(cardLayout, container);
        //add the menu to the layout
        container.add(menu, "MENU");

        add(container);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMain::new);
    }
}