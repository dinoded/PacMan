package Game.Entity;

import Game.Frame.GamePanel;

import javax.swing.*;

public class Entity extends JLabel {
    GamePanel gamePanel;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setBounds(32, 32, 32, 32);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
