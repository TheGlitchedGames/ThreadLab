package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JButton playButton;
    private JButton stopButton;

    public ControlPanel(Controller controller) {
        playButton = new PlayButton();
        stopButton = new StopButton();

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.play();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stop();
            }
        });

        add(playButton);
        add(stopButton);
    }
}