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

class GamePanel extends JPanel implements ActionListener, KeyListener {

    private javax.swing.Timer timer;
    private Player player;
    private ArrayList<Coin> coins;
    private ArrayList<Enemy> enemies;
    private Image background;
    private int level = 1;
    private int score = 0;
    private int totalEnemiesDefeated = 0;
    private int deaths = 0;
    private long levelStartTime;
    private double elapsedTime = 0;
    private GameFrame frame;
    private boolean[] keys = new boolean[256];
    private CardLayout cardLayout;
    private JPanel container;
    private JButton restartButton;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
        setFocusable(true);
        addKeyListener(this);
        
        restartButton = new JButton("Restart");
        restartButton.setBounds(500,12,85,25);
        restartButton.setFocusable(false);
        restartButton.setBackground(new Color(192, 57, 43));
        restartButton.setForeground(Color.WHITE);
        
        restartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                restartFullGame();
            }
        });
        
        add(restartButton);

        try {
            background = ImageIO.read(new File("bg.png"));
        } catch (IOException e) {
            System.out.println("Background image not found");
        }

        initLevel();
        timer = new javax.swing.Timer(16, this);
    }
    
    public void startLoop() {
        levelStartTime = System.currentTimeMillis();
        timer.start();
    }
    
    private void initLevel() {
        player = new Player(50, 250);
        coins = new ArrayList<>();
        enemies = new ArrayList<>();
        Random rand = new Random();
        
        for (int i = 0; i < 5; i++) {
            coins.add(new Coin(100 + rand.nextInt(400), 100 + rand.nextInt(300)));
        }
        
        if (level == 1) {
            for (int i = 0; i < 4; i++) {
                enemies.add(new HorizontalEnemy(200 + rand.nextInt(300), 70 + rand.nextInt(350)));
            }
        } else if (level == 2) {
            for (int i = 0; i < 3; i++) {
                enemies.add(new ChaserEnemy(300 + rand.nextInt(200), 70 + rand.nextInt(350), player));
            }
        } else if (level == 3) {
            for (int i = 0; i < 4; i++) {
                enemies.add(new PatrolEnemy(200 + rand.nextInt(300), 70 + rand.nextInt(350)));
            } 
        }
    }
    
    public void startNextLevel() {
        levelStartTime = System.currentTimeMillis();
        initLevel();
        timer.start();
    }
    
    private void restartFullGame(){
        timer.stop();
        level = 1;
        score = 0;
        deaths = 0;
        totalEnemiesDefeated = 0;
        elapsedTime = 0;
        initLevel();
        levelStartTime = System.currentTimeMillis();
        timer.start();
    }

    private void updatePlayer() {
        int dx = 0;
        int dy = 0;
        if (keys[KeyEvent.VK_LEFT]) {
            dx = -1;
        }
        if (keys[KeyEvent.VK_RIGHT]) {
            dx = 1;
        }
        if (keys[KeyEvent.VK_DOWN]) {
            dy = 1;
        }
        if (keys[KeyEvent.VK_UP]) {
            dy = -1;
        }
        if (player != null) {
            player.move(dx, dy, 600, 500);
        }
    }

    private void checkCollisions() {
        if (player == null || coins == null || enemies == null) {
            return;
        }

        for (int i = coins.size() - 1; i >= 0; i--) {
            if (player.getBounds().intersects(coins.get(i).getBounds())) {
                score++;
                coins.remove(i);
            }
        }

        for (Enemy enemy : enemies) {
            if (player.getBounds().intersects(enemy.getBounds())) {
                timer.stop();
                deaths++;
    
                JOptionPane.showMessageDialog(this, "Game Over!\nScore: " + score);
                initLevel();
                levelStartTime = System.currentTimeMillis();
                timer.start();
                return;
            }
        }
        
        if (coins.isEmpty()) {
            timer.stop();
            totalEnemiesDefeated += enemies.size();
            saveScore();

            if (level < 3) {
                level++;
                frame.getShopScreen().updateLabel();
                frame.showScreen("Shop");
            } else {
                JOptionPane.showMessageDialog(this, "Score stored in scorecards.txt");
                restartFullGame();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        updatePlayer();
        for (Enemy enemy : enemies) {
            enemy.move();
        }
        elapsedTime = (System.currentTimeMillis() - levelStartTime) / 1000.0;
        checkCollisions();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (frame == null || player == null || coins == null || enemies == null) {
            return;
        }

        if (background != null && background.getWidth(null) > 0) {
            g.drawImage(background, 0, 50, 600, 450, this);
        } else {
            g.setColor(new Color(22, 160, 133));
            g.fillRect(0, 50, 600, 450);
        }
        
        g.setColor(new Color(52, 73, 94));
        g.fillRect(0, 0, 600, 50);

        g.setColor(Color.WHITE);
        g.drawString("User: " + frame.getUser(), 15, 28);
        g.drawString("Coins: " + score, 210, 28);
        g.drawString("Level: " + level, 130, 28);
        g.drawString("Total Deaths: " + deaths, 490, 28);
        g.drawString("Time: " + String.format("%.1fs", elapsedTime), 330, 28);
        

        for (Coin coin : coins) {
            coin.draw(g);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        
        player.draw(g);

    }

    private void saveScore() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("scorecards.txt", true))) {
            writer.println("User: " + frame.getUser() + " | Completed Level: " + level + " | Coins: " + score + " | Time: " + String.format("%.2fs", elapsedTime) + " | Deaths: " + deaths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        return score;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void deductScore(int x) {
        this.score -= x;
    }
    
    public Player getPlayer() {
        return player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 256) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 256) {
            keys[e.getKeyCode()] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}