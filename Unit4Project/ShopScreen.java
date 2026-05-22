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

class ShopScreen extends JPanel{
    private GameFrame frame;
    private JLabel status;
    
    public ShopScreen(GameFrame frame){
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(39, 174, 96));
        
        status = new JLabel("Buy a Boost", JLabel.CENTER);
        status.setForeground(Color.WHITE);
        add(status, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        
        JButton buySpeed = new JButton("Buy +2 Speed | Cost: 2 Coins");
        JButton nextLevelBtn = new JButton("Next Level");
        
        buttonPanel.add(buySpeed);
        buttonPanel.add(nextLevelBtn);
        add(buttonPanel, BorderLayout.CENTER);
        
        buySpeed.addActionListener(e ->{
            GamePanel gp = frame.getGamePanel();
            if(gp.getScore() >= 5){
                gp.deductScore(5);
                gp.getPlayer().setSpeed(gp.getPlayer().getSpeed() + 2);
                updateLabel();
                JOptionPane.showMessageDialog(this, "Boost purchased");
            }else{
                JOptionPane.showMessageDialog(this, "Not enough coins");
            }
        });
        
        nextLevelBtn.addActionListener(e -> {
            frame.showScreen("Game");
            frame.getGamePanel().startNextLevel();
        });
        
    }
    
    public void updateLabel(){
        status.setText("Balance: " + frame.getGamePanel().getScore() + "Coins | Ready to Start" + frame.getGamePanel().getLevel() + "?");
    }
}