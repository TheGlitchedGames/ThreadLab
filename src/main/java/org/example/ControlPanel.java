package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JButton playButton;
    private JButton stopButton;
    private JButton updateButton;

    public ControlPanel(Controller controller) {
        playButton = new PlayButton();
        stopButton = new StopButton();
        updateButton =new UpdateButton();

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

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.update();
            }
        });

        add(playButton);
        add(stopButton);
        add(updateButton);
    }
}