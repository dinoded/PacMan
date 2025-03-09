package Game.Pickables;

import Game.Frame.GamePanel;
import Game.Pacman.Pacman;
import Game.Pickable;
import Game.Threads.Animation.Animation;

public class ScoreBuff extends Pickable {

    public ScoreBuff(int x, int y) {
        super(x, y);
        Animation animation = new Animation(this, 0);
        animation.addFrameFromFile("sprites/Buffs/Buff_ BonusPoints.png");
        animation.updateImage();
    }

    @Override
    public void apply(GamePanel gamePanel, Pacman pacman) {
        gamePanel.applyScoreBuff();
        super.apply(gamePanel, pacman);
    }
}
