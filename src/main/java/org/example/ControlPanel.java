package org.example;

import javax.swing.*;

public class ControlPanel extends JPanel {
    private JButton playButton;
    private JButton stopButton;

    public ControlPanel() {
        playButton = new PlayButton();
        stopButton = new StopButton();

        add(playButton);
        add(stopButton);
    }
}