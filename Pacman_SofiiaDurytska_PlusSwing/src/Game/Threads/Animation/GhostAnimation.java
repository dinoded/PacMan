package Game.Threads.Animation;

import Game.Enums.Directions;
import Game.Enums.GhostColors;
import Game.Ghost.Ghost;

public class GhostAnimation extends StatefulAnimation {
    private static final int DELAY = 100;

    public GhostAnimation(Ghost ghost, GhostColors color) {
        super(ghost, DELAY);
        setCurrentState(Directions.Top.ordinal());
        addFramesFromFiles("sprites/Ghost/Animation/" + color + "/" + color + "_top.png");
        setCurrentState(Directions.Right.ordinal());
        addFramesFromFiles("sprites/Ghost/Animation/" + color + "/" + color + "_right.png");
        setCurrentState(Directions.Bottom.ordinal());
        addFramesFromFiles("sprites/Ghost/Animation/" + color + "/" + color + "_down.png");
        setCurrentState(Directions.Left.ordinal());
        addFramesFromFiles("sprites/Ghost/Animation/" + color + "/" + color + "_left.png");
    }
}
