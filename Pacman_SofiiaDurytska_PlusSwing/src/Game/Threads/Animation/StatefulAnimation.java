package Game.Threads.Animation;

import Game.Entity.Entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StatefulAnimation extends Animation {
    private final List<List<ImageIcon>> animations;
    private int currentState;

    public StatefulAnimation(Entity entity, int frameDelay) {
        super(entity, frameDelay);
        animations = new ArrayList<>();
        currentState = 0;
    }

    public void setCurrentState(int stateIndex) {
        while (animations.size() <= stateIndex) animations.add(new ArrayList<>());
        animations.set(currentState, frames);
        currentState = stateIndex;
        frames = animations.get(currentState);
    }
}
