package org.example;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    private DataPanel dataPanel;
    private ConfigurationPanel configurationPanel;
    private Viewer viewer;
    private MyModel model;

    public MyView(Resource resource, Controller controller, MyModel model) {
        this.model = model;
        setTitle("ThreadLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setSize(1200, 800);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        dataPanel = new DataPanel();
        configurationPanel = new ConfigurationPanel(model);
        viewer = new Viewer(model);

        // DataPanel at the top left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.4;
        gbc.weighty = 0.9;
        add(dataPanel, gbc);

        // ConfigurationPanel in the center
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.9;
        add(configurationPanel, gbc);

        // Viewer on the right
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.4;
        gbc.weighty = 0.9;
        add(viewer, gbc);

        // ControlPanel at the bottom
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(new ControlPanel(controller), gbc);

        setVisible(true);
    }

    public void updateData() {
        configurationPanel.updateResourceData();
        dataPanel.updateData(model);
        viewer.updateTables(model);
    }
}
