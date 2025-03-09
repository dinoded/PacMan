package Game.Ghost;

import Game.Entity.MovableEntity;
import Game.Enums.Directions;
import Game.Enums.GhostColors;
import Game.Frame.GamePanel;
import Game.Threads.Animation.GhostAnimation;
import Game.Threads.GhostAI;
import Game.Threads.GhostCollidePlayer;
import Game.Threads.Movement;

public class Ghost extends MovableEntity {
    GhostAI ai;
    GhostCollidePlayer ghostCollidePlayer;

    public Ghost(GamePanel gamePanel, GhostColors color) {
        super(gamePanel);
        this.animation = new GhostAnimation(this, color);
        this.movement = new Movement(this);
        this.movement.setBounce(true);
        this.ghostCollidePlayer = new GhostCollidePlayer(this);
        this.ai = new GhostAI(this);
        setDirection(Directions.Right);
        this.animation.start();
        this.movement.start();
        this.ai.start();
        this.ghostCollidePlayer.start();
    }

    public void applySlowBuff() {
        this.movement.setSpeed(0.5F);
    }

    public void die() {
        this.animation.interrupt();
        this.movement.interrupt();
        this.ai.interrupt();
        this.ghostCollidePlayer.interrupt();
    }
}
