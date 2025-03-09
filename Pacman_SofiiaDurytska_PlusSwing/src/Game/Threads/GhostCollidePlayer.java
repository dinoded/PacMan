package Game.Threads;

import Game.Frame.GamePanel;
import Game.Ghost.Ghost;

import java.util.Random;

public class GhostCollidePlayer extends Thread {
    private static final int DELAY = 40;
    private static final Random RANDOM = new Random();
    Ghost ghost;
    GamePanel gamePanel;

    public GhostCollidePlayer(Ghost ghost) {
        this.ghost = ghost;
        this.gamePanel = ghost.getGamePanel();
    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            if (gamePanel.isPacman(ghost.getX() + 16, ghost.getY() + 16)) {
                gamePanel.handleGhostCollision();
            }

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                System.out.println("GhostAI thread interrupted");
                interrupt();
                break;
            }
        }
    }
}
