package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private PlayButton playButton;
    private StopButton stopButton;

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        initButtons();
    }

    private void initButtons() {
        playButton = new PlayButton();
        stopButton = new StopButton();

        add(playButton);
        add(stopButton);
    }

    public PlayButton getPlayButton() { return playButton; }
    public StopButton getStopButton() { return stopButton; }
}
