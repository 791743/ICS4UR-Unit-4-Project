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

class MenuPanel extends JPanel {

    public MenuPanel(CardLayout cardLayout, JPanel container) {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("COIN DODGE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));

        JButton start = new JButton("Start Game");

        JTextArea instructions = new JTextArea();
        instructions.setEditable(false);
        instructions.setText(
                "Controls:\n" +
                "WASD or Arrow Keys to move\n" +
                "Collect coins\n" +
                "Avoid enemies\n" +
                "Press B to buy speed boost (5 coins)\n" +
                "3 levels increase difficulty\n"
        );

        start.addActionListener(e -> {
            GamePanel game = new GameFrame().getGamePanel();

            container.add(game, "GAME");
            cardLayout.show(container, "GAME");

            game.requestFocusInWindow();
        });

        add(title, BorderLayout.NORTH);
        add(instructions, BorderLayout.CENTER);
        add(start, BorderLayout.SOUTH);
    }
}