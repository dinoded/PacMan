package Game.Threads;

import Game.Frame.GameFrame;

import javax.swing.*;
import java.awt.*;

public class TimePasser extends Thread {
    static final long DELAY = 1000;
    GameFrame gameFrame;

    public TimePasser(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
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
                gameFrame.changeTime(1);
            });
        }
    }
}
