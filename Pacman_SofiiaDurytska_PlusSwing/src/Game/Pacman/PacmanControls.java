package Game.Pacman;

import Game.Enums.Directions;

import javax.swing.*;
import java.awt.event.ActionEvent;

class PacmanControls {
    private final Pacman pacman;

    PacmanControls(Pacman pacman) {
        this.pacman = pacman;
    }

    private void addAction(String keyStroke, Directions direction) {
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacman.setDirection(direction);
            }
        };

        JLayeredPane panel = pacman.getGamePanel();
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStroke), "move" + direction);
        panel.getActionMap().put("move" + direction, action);
    }

    void setup() {
        addAction("UP", Directions.Top);
        addAction("RIGHT", Directions.Right);
        addAction("DOWN", Directions.Bottom);
        addAction("LEFT", Directions.Left);
        addAction("W", Directions.Top);
        addAction("D", Directions.Right);
        addAction("S", Directions.Bottom);
        addAction("A", Directions.Left);
    }
}
