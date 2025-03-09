package Flow;

import javax.swing.*;

public class MainMenu extends JFrame {

    private final JPanel mainPanel;

    public MainMenu() {
        setTitle("Dino Flow.PacMan");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        showMainMenu();

        add(mainPanel);
        setVisible(true);
    }

    public void showMainMenu() {
        mainPanel.removeAll();

        JButton newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(CENTER_ALIGNMENT);
        newGameButton.addActionListener(e -> showBoardSizeSelection());

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setAlignmentX(CENTER_ALIGNMENT);
        highScoresButton.addActionListener(e -> showHighScores());

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(newGameButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(highScoresButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(exitButton);
        mainPanel.add(Box.createVerticalGlue());

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showBoardSizeSelection() {
        mainPanel.removeAll();
        mainPanel.add(new BoardSizeSelection(this));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showHighScores() {
        mainPanel.removeAll();
        mainPanel.add(new HighScores(this));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}