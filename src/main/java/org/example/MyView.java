package org.example;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    public MyView(Resource resource) {
        setTitle("ThreadLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setSize(1200, 800);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // DataPanel at the top left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.4; // 2/5 of the width
        gbc.weighty = 0.9;
        add(new DataPanel(), gbc);

        // ConfigurationPanel in the center
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2; // 1/5 of the width
        gbc.weighty = 0.9;
        add(new ConfigurationPanel(resource), gbc);

        // Viewer on the right
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.4; // 2/5 of the width
        gbc.weighty = 0.9;
        add(new Viewer(), gbc);

        // ControlPanel at the bottom
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(new ControlPanel(), gbc);

        setVisible(true);
    }
}