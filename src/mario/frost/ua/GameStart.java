package mario.frost.ua;

import javax.swing.*;

public class GameStart {
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        JFrame startFrame = new JFrame("BubleShooter2D");
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        startFrame.setContentPane(gamePanel);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null);

        startFrame.setVisible(true);
        gamePanel.start();

    }
}
