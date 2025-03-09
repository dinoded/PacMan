package Game.Pickables;

import Game.Frame.GamePanel;
import Game.Pacman.Pacman;
import Game.Pickable;
import Game.Threads.Animation.Animation;

public class SlowBuff extends Pickable {

    public SlowBuff(int x, int y) {
        super(x, y);
        Animation animation = new Animation(this, 0);
        animation.addFrameFromFile("sprites/Buffs/Buff_Slowdown.png");
        animation.updateImage();
    }

    @Override
    public void apply(GamePanel gamePanel, Pacman pacman) {
        gamePanel.applySlowBuff();
        super.apply(gamePanel, pacman);
    }
}
