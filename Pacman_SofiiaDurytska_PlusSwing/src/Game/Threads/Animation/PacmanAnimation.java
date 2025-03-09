package Game.Threads.Animation;

import Game.Enums.Directions;
import Game.Pacman.Pacman;

public class PacmanAnimation extends StatefulAnimation {
    private static final int DELAY = 100;

    public PacmanAnimation(Pacman pacman) {
        super(pacman, DELAY);
        setCurrentState(Directions.Top.ordinal());
        addFramesFromFiles("sprites/Player/Animation/top/top_1.png",
                "sprites/Player/Animation/top/top_2.png",
                "sprites/Player/Animation/top/top_3.png");
        setCurrentState(Directions.Right.ordinal());
        addFramesFromFiles("sprites/Player/Animation/right/right_1.png",
                "sprites/Player/Animation/right/right_2.png",
                "sprites/Player/Animation/right/right_3.png");
        setCurrentState(Directions.Bottom.ordinal());
        addFramesFromFiles("sprites/Player/Animation/down/down_1.png",
                "sprites/Player/Animation/down/down_2.png",
                "sprites/Player/Animation/down/down_3.png");
        setCurrentState(Directions.Left.ordinal());
        addFramesFromFiles("sprites/Player/Animation/left/left_1.png",
                "sprites/Player/Animation/left/left_2.png",
                "sprites/Player/Animation/left/left_3.png");
    }
}
