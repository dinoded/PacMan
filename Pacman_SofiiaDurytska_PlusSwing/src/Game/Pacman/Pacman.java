package Game.Pacman;

import Game.Entity.MovableEntity;
import Game.Enums.Directions;
import Game.Frame.GamePanel;
import Game.Threads.Animation.PacmanAnimation;
import Game.Threads.Movement;
import Game.Threads.PacmanPickup;

public class Pacman extends MovableEntity {
    PacmanControls controls;
    PacmanPickup pickup;

    public Pacman(GamePanel gamePanel) {
        super(gamePanel);
        this.animation = new PacmanAnimation(this);
        this.controls = new PacmanControls(this);
        this.movement = new Movement(this);
        this.pickup = new PacmanPickup(this);
        setDirection(Directions.Right);
        this.animation.start();
        this.movement.start();
        this.pickup.start();
    }

    public void addNotify() {
        super.addNotify();
        this.controls.setup();
    }

    public void applySpeedBuff() {
        this.movement.setSpeed(2);
    }

    public void die() {
        this.animation.interrupt();
        this.movement.interrupt();
        this.pickup.interrupt();
    }
}
