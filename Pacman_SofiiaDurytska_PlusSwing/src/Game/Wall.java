package Game;

import Game.Threads.Animation.Animation;

import javax.swing.*;

public class Wall extends JLabel {

    static private final int CELL_SIZE = 32;

    public Wall(int x, int y) {
        setBounds(x, y, CELL_SIZE, CELL_SIZE);
        Animation animation = new Animation(this, 0);
        animation.addFrameFromFile("sprites/wall.png");
        animation.updateImage();
    }
}

