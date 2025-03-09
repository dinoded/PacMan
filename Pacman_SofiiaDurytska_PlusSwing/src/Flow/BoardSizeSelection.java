package Flow;

import Game.Frame.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSizeSelection extends JPanel {

    static int[][] map1 = {
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 3, 0, 1},
            {1, 2, 1, 1, 6, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1}
    };

    static int[][] map2 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 6, 0, 0, 4, 1},
            {1, 0, 1, 1, 0, 1, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 5, 0, 0, 3, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    static int[][] map3 = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 5, 1, 1, 1, 1, 6, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 4, 1, 1, 3, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
    };

    static int[][] map4 = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 5, 1, 1, 1, 1, 6, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 4, 1, 1, 3, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
    };

    static int[][] map5 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 4, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 4, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 3, 1, 0, 0, 1, 0, 1, 5, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1},
            {1, 6, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    MainMenu mainMenu;

    public BoardSizeSelection(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select Board Size");
        label.setAlignmentX(CENTER_ALIGNMENT);

        JButton size1Button = new JButton("1 level");
        size1Button.setAlignmentX(CENTER_ALIGNMENT);
        size1Button.addActionListener(e -> startGame(map1));

        JButton size2Button = new JButton("2 level");
        size2Button.setAlignmentX(CENTER_ALIGNMENT);
        size2Button.addActionListener(e -> startGame(map2));

        JButton size3Button = new JButton("3 level");
        size3Button.setAlignmentX(CENTER_ALIGNMENT);
        size3Button.addActionListener(e -> startGame(map3));

        JButton size4Button = new JButton("4 level");
        size4Button.setAlignmentX(CENTER_ALIGNMENT);
        size4Button.addActionListener(e -> startGame(map4));

        JButton size5Button = new JButton("5 level");
        size5Button.setAlignmentX(CENTER_ALIGNMENT);
        size5Button.addActionListener(e -> startGame(map5));

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.showMainMenu();  // Возвращаемся в главное меню
            }
        });

        add(Box.createVerticalGlue());
        add(label);
        add(Box.createVerticalStrut(10));
        add(size1Button);
        add(Box.createVerticalStrut(10));
        add(size2Button);
        add(Box.createVerticalStrut(10));
        add(size3Button);
        add(Box.createVerticalStrut(10));
        add(size4Button);
        add(Box.createVerticalStrut(10));
        add(size5Button);
        add(Box.createVerticalStrut(10));
        add(backButton);
        add(Box.createVerticalGlue());
    }

    private void startGame(int[][] map) {
        mainMenu.dispose();
        SwingUtilities.invokeLater(() -> new GameFrame(map));
    }
}
