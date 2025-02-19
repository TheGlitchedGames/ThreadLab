package org.example;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    private DataPanel dataPanel;
    private ConfigurationPanel configurationPanel;
    private Viewer viewer;
    private MyModel model;

    public MyView(Controller controller, MyModel model) {
        this.model = model;
        setTitle("Resource Management Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setSize(1200, 800);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // ConfigurationPanel a la izquierda
        configurationPanel = new ConfigurationPanel(model);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.2;
        gbc.weighty = 0.9;
        add(configurationPanel, gbc);

        // DataPanel en el centro
        dataPanel = new DataPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.3;
        add(dataPanel, gbc);

        // Viewer a la derecha
        viewer = new Viewer(model);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 0.9;
        add(viewer, gbc);

        // ControlPanel en la parte inferior
        ControlPanel controlPanel = new ControlPanel(controller);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(controlPanel, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateData() {
        dataPanel.updateData(model);
        viewer.updateTables(model);
    }
}