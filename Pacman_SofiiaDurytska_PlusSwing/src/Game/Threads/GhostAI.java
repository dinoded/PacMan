package Game.Threads;

import Game.Enums.Directions;
import Game.Ghost.Ghost;

import java.awt.*;
import java.util.Random;

public class GhostAI extends Thread {
    private static final int DELAY = 2000;
    private static final Random RANDOM = new Random();
    Ghost ghost;

    public GhostAI(Ghost ghost) {
        this.ghost = ghost;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Toolkit.getDefaultToolkit().sync();
            Directions[] directions = Directions.values();
            ghost.setDirection(directions[RANDOM.nextInt(directions.length)]);
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
