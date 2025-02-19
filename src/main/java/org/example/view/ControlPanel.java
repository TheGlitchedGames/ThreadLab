package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private PlayButton playButton;
    private StopButton stopButton;

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initButtons();
    }

    private void initButtons() {
        playButton = new PlayButton();
        stopButton = new StopButton();

        // Style buttons
        styleButton(playButton, new Color(46, 204, 113));
        styleButton(stopButton, new Color(231, 76, 60));

        // Initially disable stop button
        stopButton.setEnabled(false);

        add(playButton);
        add(stopButton);
    }

    private void styleButton(JButton button, Color color) {
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
    }

    public PlayButton getPlayButton() { return playButton; }
    public StopButton getStopButton() { return stopButton; }
}