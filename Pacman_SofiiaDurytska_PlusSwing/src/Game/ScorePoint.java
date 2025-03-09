package Game;

import Game.Frame.GamePanel;
import Game.Pacman.Pacman;
import Game.Threads.Animation.Animation;

public class ScorePoint extends Pickable {

    public ScorePoint(int x, int y) {
        super(x, y);
        Animation animation = new Animation(this, 0);
        animation.addFrameFromFile("sprites/Points/point.png");
        animation.updateImage();
    }

    @Override
    public void apply(GamePanel gamePanel, Pacman pacman) {
        gamePanel.changeScore(1);
        super.apply(gamePanel, pacman);
    }
}
