package Game.Frame;

import Flow.MainMenu;
import Game.Threads.TimePasser;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final JLabel healthLabel;
    private final JLabel scoreLabel;
    private final JLabel timeLabel;
    private final JButton backButton;
    private final TimePasser timePasser;
    GamePanel gamePanel;
    private int seconds = 0;

    public GameFrame(int[][] gameMap) {
        setTitle("Pacman Game");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel(this, gameMap);
        add(gamePanel, BorderLayout.CENTER);

        healthLabel = new JLabel();
        scoreLabel = new JLabel();
        timeLabel = new JLabel();
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            gamePanel.resetEntities();
            die();
            gamePanel.die();
            dispose();
            SwingUtilities.invokeLater(MainMenu::new);
        });
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        setupLabels();
        pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - getWidth()) / 2;
        int centerY = (screenSize.height - getHeight()) / 2;

        setLocation(centerX, centerY);

        setTime(seconds);

        timePasser = new TimePasser(this);
        timePasser.start();

        setVisible(true);
    }

    public void setScore(int score) {
        SwingUtilities.invokeLater(() -> {
            scoreLabel.setText("Score: " + score);
            scoreLabel.repaint();
        });
    }

    public void setHealth(int health) {
        SwingUtilities.invokeLater(() -> {
            healthLabel.setText("Health: " + health);
            repaint();
        });
    }

    public void changeTime(int deltaSeconds) {
        seconds += deltaSeconds;
        setTime(seconds);
    }

    private void setTime(int seconds) {
        int minutes = seconds / 60;
        int finalSeconds = seconds % 60;
        SwingUtilities.invokeLater(() -> {
            timeLabel.setText(minutes + "m " + finalSeconds + "s");
            repaint();
        });
    }

    private void setupLabels() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 3));
        topPanel.setAlignmentX(CENTER_ALIGNMENT);

        add(backButton, BorderLayout.NORTH);
        topPanel.add(healthLabel);
        topPanel.add(scoreLabel);
        topPanel.add(timeLabel);

        add(topPanel, BorderLayout.SOUTH);
    }

    public void die() {
        timePasser.interrupt();
    }
}
