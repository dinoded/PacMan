package Game.Threads.Animation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Animation extends Thread {
    protected List<ImageIcon> frames;
    protected JLabel entity;
    protected int currentFrameIndex;
    protected long frameDelay;

    public Animation(JLabel entity, int frameDelay) {
        this.entity = entity;
        this.frameDelay = frameDelay;
        this.frames = new ArrayList<>();
        this.currentFrameIndex = 0;
    }

    public void addFrame(ImageIcon frame) {
        frames.add(frame);
    }

    public void addFrameFromFile(String filename) {
        System.out.println(System.getProperty("user.dir") + "/" + filename);
        addFrame(new ImageIcon(System.getProperty("user.dir") + "/" + filename));
    }

    public void addFramesFromFiles(String... filenames) {
        for (String filename : filenames) {
            addFrameFromFile(filename);
        }
    }

    public void updateImage() {
        ImageIcon currentFrame = frames.get(currentFrameIndex);
        SwingUtilities.invokeLater(() -> entity.setIcon(currentFrame));
        currentFrameIndex = (currentFrameIndex + 1) % frames.size();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Toolkit.getDefaultToolkit().sync();
            updateImage();
            try {
                Thread.sleep(frameDelay);
            } catch (InterruptedException e) {
                System.out.println("Animation interrupted");
                interrupt();
                break;
            }
        }
    }
}
