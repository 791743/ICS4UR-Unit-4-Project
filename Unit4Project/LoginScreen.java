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
import java.awt.Font;

class LoginScreen extends JPanel{
    private JTextField userField;
    private JPasswordField passField;
    private GameFrame frame;
    private final String infoFile = "users.text";
    
    public LoginScreen(GameFrame frame){
        this.frame = frame;
        setLayout(new GridBagLayout());
        setBackground(new Color(44,62,80));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titleLabel = new JLabel("EXOPLANET GAME LOGIN", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        
        JLabel uLabel = new JLabel("Username:");
        uLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(uLabel, gbc);
        userField = new JTextField(15);
        gbc.gridx = 1;
        add(userField, gbc);
        
        JLabel pLabel = new JLabel("Password:");
        pLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(pLabel, gbc);
        passField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passField, gbc);
        
        JButton loginBtn = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginBtn, gbc);
        
        JButton regBtn = new JButton("Register");
        gbc.gridx = 1;
        add(regBtn, gbc);
        
        loginBtn.addActionListener(e -> handleLogin());
        regBtn.addActionListener(e -> handleRegister());
    }
    
    private void handleLogin(){
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword()).trim();
        if(verifyUser(user, pass)){
            frame.setUser(user);
            frame.showScreen("Game");
            frame.startGameLoop();
        }else{
            JOptionPane.showMessageDialog(this, "Invalid credentials or account non-existent.");
        }
    }
    
    private void handleRegister(){
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword()).trim();
        if(user.isEmpty() || pass.isEmpty()){
            JOptionPane.showMessageDialog(this, "Fields cannot be left blank.");
            return;
        }
        
        try(PrintWriter out = new PrintWriter(new FileWriter(infoFile, true))){
            out.println(user + "," + pass);
            JOptionPane.showMessageDialog(this, "Registration Complete! Please sign in now.");
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "File error");
        }
        
    }
    
    private boolean verifyUser(String user, String pass){
        File file = new File(infoFile);
        if(!file.exists()){
            return false;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] split = line.split(",");
                if(split.length == 2 && split[0].equals(user) && split[1].equals(pass)){
                    return true;
                }
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }
}