package Game.Pickables;

import Game.Frame.GamePanel;
import Game.Pacman.Pacman;
import Game.Pickable;
import Game.Threads.Animation.Animation;

public class HealthBuff extends Pickable {

    public HealthBuff(int x, int y) {
        super(x, y);
        Animation animation = new Animation(this, 0);
        animation.addFrameFromFile("sprites/Buffs/Buff_PlusHp.png");
        animation.updateImage();
    }

    @Override
    public void apply(GamePanel gamePanel, Pacman pacman) {
        gamePanel.changeHelth(1);
        super.apply(gamePanel, pacman);
    }
}
