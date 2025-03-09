package Game.Pickables;

import Game.Frame.GamePanel;
import Game.Pacman.Pacman;
import Game.Pickable;
import Game.Threads.Animation.Animation;

public class ImmortalBuff extends Pickable {

    public ImmortalBuff(int x, int y) {
        super(x, y);
        Animation animation = new Animation(this, 0);
        animation.addFrameFromFile("sprites/Buffs/Buff_Invincibility.png");
        animation.updateImage();
    }

    @Override
    public void apply(GamePanel gamePanel, Pacman pacman) {
        gamePanel.applyImmortalBuff();
        super.apply(gamePanel, pacman);
    }
}
