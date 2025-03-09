package Game.Threads;

import Game.Entity.MovableEntity;
import Game.Enums.Directions;
import Game.Frame.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Movement extends Thread {
    private static final Random RANDOM = new Random();
    private static final int DELAY = 16;
    MovableEntity entity;
    GamePanel gamePanel;
    float speed = 1;
    Directions direction = Directions.Top;
    Directions nextDirection = Directions.Top;
    boolean bounce = false;

    public Movement(MovableEntity entity) {
        this.entity = entity;
        this.gamePanel = entity.getGamePanel();
    }

    public void setBounce(boolean bounce) {
        this.bounce = bounce;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDirection(Directions dir) {
        this.nextDirection = dir;
    }

    public boolean canMove(int dx, int dy) {
        int newX = entity.getX() + dx;
        int newY = entity.getY() + dy;
        return !gamePanel.isWall(newX, newY) && !gamePanel.isWall(newX + entity.getWidth() - 1, newY) &&
                !gamePanel.isWall(newX, newY + entity.getHeight() - 1) && !gamePanel.isWall(newX + entity.getWidth() - 1, newY + entity.getHeight() - 1);
    }

    public boolean canMove(Directions dir) {
        return canMove(getDx(dir), getDy(dir));
    }

    public int getDx(Directions dir) {
        return switch (dir) {
            case Directions.Right -> 1;
            case Directions.Left -> -1;
            default -> 0;
        };
    }

    public int getDx() {
        return getDx(direction);
    }

    public int getDy(Directions dir) {
        return switch (dir) {
            case Directions.Bottom -> 1;
            case Directions.Top -> -1;
            default -> 0;
        };
    }

    public int getDy() {
        return getDy(direction);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Toolkit.getDefaultToolkit().sync();

            if (direction != nextDirection && canMove(nextDirection)) {
                direction = nextDirection;
                entity.setAnimationState(direction.ordinal());
            }

            SwingUtilities.invokeLater(() -> {
                if (canMove(direction)) {
                    entity.setLocation(entity.getX() + getDx(), entity.getY() + getDy());
                } else if (bounce) {
                    Directions[] directions = Directions.values();
                    entity.setDirection(directions[RANDOM.nextInt(directions.length)]);
                }
                if (speed != 1.0f) {
                    if (speed > 1.0f) {
                        speed = Math.max(1.0f, speed - 0.002f);
                    } else {
                        speed = Math.min(1.0f, speed + 0.002f);
                    }
                }
            });

            try {
                Thread.sleep((long) (DELAY / speed));
            } catch (InterruptedException e) {
                System.out.println("Movement interrupted");
                interrupt();
                break;
            }
        }
    }
}
