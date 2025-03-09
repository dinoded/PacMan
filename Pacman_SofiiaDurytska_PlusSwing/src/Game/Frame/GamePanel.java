package Game.Frame;

import Flow.GameOver;
import Game.Enums.GhostColors;
import Game.Ghost.Ghost;
import Game.Pacman.Pacman;
import Game.Pickable;
import Game.Pickables.*;
import Game.ScorePoint;
import Game.Threads.Spawner;
import Game.Wall;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JLayeredPane {
    private static final int CELL_SIZE = 32;
    private final int[][] gameMap;
    private final Pickable[][] pickables;
    private final GameFrame gameFrame;
    private final Class<? extends Pickable>[] buffClasses = new Class[]{
            HealthBuff.class,
            ScoreBuff.class,
            SlowBuff.class,
            SpeedBuff.class,
            ImmortalBuff.class,
    };
    private final Spawner spawner;
    private final ArrayList<Ghost> ghosts;
    private int score = 0;
    private int health = 3;
    private Pacman pacman;
    private boolean scoreBuffActive = false;
    private boolean immortalBuffActive = false;
    private Thread scoreBuffThread;
    private Thread immortalBuffThread;
    private int coinLeft = 0;

    GamePanel(GameFrame gameFrame, int[][] gameMap) {
        this.gameFrame = gameFrame;
        this.gameMap = gameMap;
        this.spawner = new Spawner(this);
        setLayout(null);
        setPreferredSize(new Dimension(getMapWidth(), getMapHeight()));
        setBackground(Color.BLACK);
        setOpaque(true);
        this.pickables = new Pickable[getMapWidth()][getMapHeight()];
        this.ghosts = new ArrayList<>();
        setupMap();
        this.spawner.start();
        changeHelth(0);
        changeScore(0);
    }

    public int getMapWidth() {
        return CELL_SIZE * gameMap[0].length;
    }

    public int getMapHeight() {
        return CELL_SIZE * gameMap.length;
    }

    public void changeScore(int delta) {
        if (scoreBuffActive && delta > 0) delta *= 2;
        score += delta;
        gameFrame.setScore(score);
    }

    public void changeHelth(int delta) {
        health += delta;
        gameFrame.setHealth(health);
    }

    private void setupMap() {
        for (int row = 0; row < gameMap.length; row++) {
            for (int col = 0; col < gameMap[0].length; col++) {
                int cellValue = gameMap[row][col];
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
                switch (cellValue) {
                    case 0:
                        addPoint(x, y);
                        break;
                    case 1:
                        addWall(x, y);
                        break;
                    case 2:
                        addPacman(x, y);
                        break;
                    case 3:
                        addPoint(x, y);
                        addGhost(x, y, GhostColors.PINK);
                        break;
                    case 4:
                        addPoint(x, y);
                        addGhost(x, y, GhostColors.RED);
                        break;
                    case 5:
                        addPoint(x, y);
                        addGhost(x, y, GhostColors.BLUE);
                        break;
                    case 6:
                        addPoint(x, y);
                        addGhost(x, y, GhostColors.ORANGE);
                        break;
                }
            }
        }
    }

    private void addPoint(int x, int y) {
        ScorePoint point = new ScorePoint(x, y);
        pickables[y / CELL_SIZE][x / CELL_SIZE] = point;
        add(point);
        coinLeft++;
    }

    private void addWall(int x, int y) {
        add(new Wall(x, y));
    }

    private void addPacman(int x, int y) {
        pacman = new Pacman(this);
        pacman.setLocation(x, y);
        add(pacman, PALETTE_LAYER);
    }

    private void addGhost(int x, int y, GhostColors color) {
        Ghost ghost = new Ghost(this, color);
        ghosts.add(ghost);
        ghost.setLocation(x, y);
        add(ghost, PALETTE_LAYER);
    }

    public boolean isWall(int x, int y) {
        int col = x / CELL_SIZE;
        int row = y / CELL_SIZE;
        return gameMap[row][col] == 1;
    }

    public boolean isPacman(int x, int y) {
        return Math.abs(pacman.getX() + 16 - x) < 16 && Math.abs(pacman.getY() + 16 - y) < 16;
    }

    public Pickable getPickable(int x, int y) {
        int col = x / CELL_SIZE;
        int row = y / CELL_SIZE;
        return pickables[row][col];
    }

    public void removePickable(int x, int y) {
        int col = x / CELL_SIZE;
        int row = y / CELL_SIZE;
        SwingUtilities.invokeLater(() -> {
            if (pickables[row][col] instanceof ScorePoint) {
                coinLeft--;
                if (coinLeft == 0) {
                    resetEntities();
                    handleEndGame();
                }
            }
            if (pickables[row][col] != null) {
                remove(pickables[row][col]);
                revalidate();
                repaint();
            }
            pickables[row][col] = null;
        });
    }

    private Class<? extends Pickable> getRandomBuffClass() {
        Random random = new Random();
        int index = random.nextInt(buffClasses.length);
        return buffClasses[index];
    }

    public void spawnBuff() {
        java.util.List<Point> emptyCells = new java.util.ArrayList<>();
        for (int row = 0; row < gameMap.length; row++) {
            for (int col = 0; col < gameMap[0].length; col++) {
                if (gameMap[row][col] == 1) continue;
                if (pickables[row][col] == null) {
                    emptyCells.add(new Point(col * CELL_SIZE, row * CELL_SIZE));
                } else if (!(pickables[row][col] instanceof ScorePoint)) {
                    return;
                }

            }
        }

        if (!emptyCells.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(emptyCells.size());
            Point randomEmptyCell = emptyCells.get(index);

            Class<? extends Pickable> selectedBuffClass = getRandomBuffClass();
            try {
                Constructor<? extends Pickable> constructor = selectedBuffClass.getConstructor(int.class, int.class);
                Pickable buff = constructor.newInstance(randomEmptyCell.x, randomEmptyCell.y);
                pickables[randomEmptyCell.y / CELL_SIZE][randomEmptyCell.x / CELL_SIZE] = buff;
                SwingUtilities.invokeLater(() -> {
                    add(buff);
                    revalidate();
                    repaint();
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void applySlowBuff() {
        for (Ghost ghost : ghosts) {
            ghost.applySlowBuff();
        }
    }

    public void applyScoreBuff() {
        scoreBuffActive = true;
        if (scoreBuffThread != null) {
            scoreBuffThread.interrupt();
        }
        scoreBuffThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                scoreBuffActive = false;
                System.out.println("Score buff expired");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        scoreBuffThread.start();
    }

    public void handleGhostCollision() {
        if (immortalBuffActive) return;
        changeHelth(-1);
        SwingUtilities.invokeLater(() -> {
            resetEntities();
            if (health == 0) {
                handleEndGame();
            } else {
                respawn();
            }
        });
    }

    void resetEntities() {
        pacman.die();
        remove(pacman);
        for (Ghost ghost : ghosts) {
            ghost.die();
            remove(ghost);
        }
        ghosts.clear();
        revalidate();
        repaint();
    }

    private void respawn() {
        for (int row = 0; row < gameMap.length; row++) {
            for (int col = 0; col < gameMap[0].length; col++) {
                int cellValue = gameMap[row][col];
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
                switch (cellValue) {
                    case 2:
                        addPacman(x, y);
                        break;
                    case 3:
                        addGhost(x, y, GhostColors.PINK);
                        break;
                    case 4:
                        addGhost(x, y, GhostColors.RED);
                        break;
                    case 5:
                        addGhost(x, y, GhostColors.BLUE);
                        break;
                    case 6:
                        addGhost(x, y, GhostColors.ORANGE);
                        break;
                }
            }
        }
    }

    private void handleEndGame() {
        die();
        gameFrame.die();
        gameFrame.dispose();
        SwingUtilities.invokeLater(() -> new GameOver(score));
    }

    public void applyImmortalBuff() {
        immortalBuffActive = true;
        if (immortalBuffThread != null) {
            immortalBuffThread.interrupt();
        }
        immortalBuffThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                immortalBuffActive = false;
                System.out.println("Immortal buff expired");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        immortalBuffThread.start();
    }

    public void die() {
        spawner.interrupt();
        if (scoreBuffThread != null) {
            scoreBuffThread.interrupt();
        }
        if (immortalBuffThread != null) {
            immortalBuffThread.interrupt();
        }
    }
}
