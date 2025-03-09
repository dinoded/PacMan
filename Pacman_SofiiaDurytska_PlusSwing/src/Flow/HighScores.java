package Flow;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

// Класс отображения результатов
public class HighScores extends JPanel {

    private final JList<String> scoreList;

    public HighScores(MainMenu mainMenu) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("High Scores");
        label.setAlignmentX(CENTER_ALIGNMENT);

        scoreList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(scoreList);

        loadScores();

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> mainMenu.showMainMenu());

        add(Box.createVerticalGlue());
        add(label);
        add(Box.createVerticalStrut(10));
        add(scrollPane);
        add(Box.createVerticalStrut(10));
        add(backButton);
        add(Box.createVerticalGlue());
    }

    private void loadScores() {
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<String> scores = readScores();
        for (String score : scores) {
            model.addElement(score);
        }
        scoreList.setModel(model);
    }

    private ArrayList<String> readScores() {
        ArrayList<String> scores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.dat"))) {
            scores = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        scores.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.split(" ")[0]);
            int scoreB = Integer.parseInt(b.split(" ")[0]);
            return Integer.compare(scoreB, scoreA);
        });
        return scores;
    }
}
