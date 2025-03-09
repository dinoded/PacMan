package Game.Pickables;

import Game.Frame.GamePanel;
import Game.Pacman.Pacman;
import Game.Pickable;
import Game.Threads.Animation.Animation;

public class SpeedBuff extends Pickable {

    public SpeedBuff(int x, int y) {
        super(x, y);
        Animation animation = new Animation(this, 0);
        animation.addFrameFromFile("sprites/Buffs/Buff_Speed.png");
        animation.updateImage();
    }

    @Override
    public void apply(GamePanel gamePanel, Pacman pacman) {
        pacman.applySpeedBuff();
        super.apply(gamePanel, pacman);
    }
}
