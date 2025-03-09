package Game;

import Game.Frame.GamePanel;
import Game.Pacman.Pacman;

import javax.swing.*;

public class Pickable extends JLabel {
    static private final int CELL_SIZE = 32;
    
    public Pickable(int x, int y) {
        setBounds(x, y, 32, 32);
    }

    public void apply(GamePanel gamePanel, Pacman pacman) {
        System.out.println("Picked up");
        gamePanel.removePickable(getX(), getY());
    }

}
