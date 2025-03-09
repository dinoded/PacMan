package Flow;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GameOver extends JFrame {
    public GameOver(int score) {
        setTitle("Game Over");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JLabel scoreLabel = new JLabel("<html>Your score: " + score + "<br>Enter your name:</html>");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(scoreLabel);

        JTextField nameField = new JTextField(" ");
        panel.add(nameField);

        JButton saveButton = new JButton("Save Score");
        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.startsWith("Enter your name:")) {
                name = name.substring(17).trim();
            }
            saveScore(name, score);
            dispose();
            new MainMenu();
        });

        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveScore(String name, int score) {
        ArrayList<String> scores = readScores();
        scores.add(score + " - " + name);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scores.dat"))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> readScores() {
        ArrayList<String> scores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.dat"))) {
            scores = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            // No scores file yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return scores;
    }
}
