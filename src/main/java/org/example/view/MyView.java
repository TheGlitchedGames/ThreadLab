package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    private ConfigurationPanel configPanel;
    private DataPanel dataPanel;
    private Viewer viewer;
    private ControlPanel controlPanel;

    public MyView() {
        setTitle("ThreadLab - Resource Management Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 800));

        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding between components

        // Configuration Panel (Left)
        configPanel = new ConfigurationPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(configPanel, gbc);

        // Data Panel (Center)
        dataPanel = new DataPanel();
        gbc.gridx = 1;
        gbc.weightx = 0.25;
        add(dataPanel, gbc);

        // Viewer Panel (Right)
        viewer = new Viewer();
        gbc.gridx = 2;
        gbc.weightx = 0.5;
        add(viewer, gbc);

        // Control Panel (Bottom)
        controlPanel = new ControlPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        add(controlPanel, gbc);
    }

    // Getters
    public ConfigurationPanel getConfigPanel() { return configPanel; }
    public DataPanel getDataPanel() { return dataPanel; }
    public Viewer getViewer() { return viewer; }
    public ControlPanel getControlPanel() { return controlPanel; }
}