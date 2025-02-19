package org.example;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton playButton;
    private JButton stopButton;

    public ControlPanel(Controller controller) {
        setLayout(new FlowLayout());

        playButton = new JButton("Play");
        stopButton = new JButton("Stop");

        // Estilo de los botones
        playButton.setPreferredSize(new Dimension(100, 40));
        stopButton.setPreferredSize(new Dimension(100, 40));

        playButton.setBackground(new Color(46, 204, 113));
        stopButton.setBackground(new Color(231, 76, 60));

        playButton.setForeground(Color.WHITE);
        stopButton.setForeground(Color.WHITE);

        playButton.setFocusPainted(false);
        stopButton.setFocusPainted(false);

        playButton.addActionListener(e -> controller.play());
        stopButton.addActionListener(e -> controller.stop());

        add(playButton);
        add(stopButton);
    }
}