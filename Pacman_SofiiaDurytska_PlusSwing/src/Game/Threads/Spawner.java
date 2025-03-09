package Game.Threads;

import Game.Frame.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Spawner extends Thread {
    static final long DELAY = 5000;
    GamePanel gamePanel;

    public Spawner(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Toolkit.getDefaultToolkit().sync();

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                System.out.println("Movement interrupted");
                interrupt();
                break;
            }

            SwingUtilities.invokeLater(() -> {
                gamePanel.spawnBuff();
            });
        }
    }
}
