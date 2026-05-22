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

//Main window class with different screens
public class GameFrame extends JFrame {
    //Card layout to go between login, game, and shop screen
    private CardLayout cardLayout;
    private JPanel containerPanel; 
    private GamePanel gamePanel;
    private ShopScreen shopPanel;
    private String user = "Guest"; // Keeps track of who is playing
    
    public GameFrame() {
        //Window setup
        setTitle("Exoplanet Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //Initialize the layout manager and the main panel
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        
        //Creating each type of screen
        LoginScreen loginPanel = new LoginScreen(this);
        gamePanel = new GamePanel(this);
        shopPanel = new ShopScreen(this);
        
        gamePanel.setPreferredSize(new Dimension(600, 500));
        
        //Add screens to panel
        containerPanel.add(loginPanel, "Login");
        containerPanel.add(gamePanel, "Game");
        containerPanel.add(shopPanel, "Shop");
        
        add(containerPanel);
        pack(); //window size
        setLocationRelativeTo(null);//centers to screen
        setVisible(true);
        
        //start on the login screen
        showScreen("Login");
    }
    
    //switching between screens
    public void showScreen(String key) {
        cardLayout.show(containerPanel, key); 
    }
    
    //getters to get the game and shop screens
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    

    public ShopScreen getShopPanel() {
        return shopPanel;
    }
    
    //game timer start
    public void startGameLoop() {
        gamePanel.startLoop();
    }
    
    //more getter and setter methods
    public void setCurrentUser(String user) {
        this.user = user;
    }
    
    public String getCurrentUser() {
        return this.user;
    }
    public void setUser(String user){ 
        this.setCurrentUser(user);
    }
    public String getUser(){ 
        return this.getCurrentUser(); 
    }
    public ShopScreen getShopScreen(){ 
        return this.getShopPanel(); 

    }
    
    //use invokeLater to avoid glitches
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame());
    }
}