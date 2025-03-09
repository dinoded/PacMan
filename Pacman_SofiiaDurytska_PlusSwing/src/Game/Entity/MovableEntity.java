package Game.Entity;

import Game.Enums.Directions;
import Game.Frame.GamePanel;
import Game.Threads.Animation.StatefulAnimation;
import Game.Threads.Movement;

public class MovableEntity extends Entity {
    protected StatefulAnimation animation;
    protected Movement movement;

    public MovableEntity(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void setAnimationState(int state) {
        animation.setCurrentState(state);
    }

    public void setDirection(Directions direction) {
        this.movement.setDirection(direction);
    }
}
