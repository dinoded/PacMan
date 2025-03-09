package Game.Threads;

import Game.Frame.GamePanel;
import Game.Pacman.Pacman;
import Game.Pickable;

import javax.swing.*;
import java.awt.*;

public class PacmanPickup extends Thread {
    static final long DELAY = 40;
    Pacman pacman;
    GamePanel gamePanel;

    public PacmanPickup(Pacman pacman) {
        this.pacman = pacman;
        this.gamePanel = pacman.getGamePanel();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Toolkit.getDefaultToolkit().sync();

            SwingUtilities.invokeLater(() -> {
                Pickable pickable = gamePanel.getPickable(pacman.getX() + 16, pacman.getY() + 16);
                if (pickable == null) return;
                pickable.apply(gamePanel, pacman);
            });

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                System.out.println("Movement interrupted");
                interrupt();
                break;
            }
        }
    }
}
