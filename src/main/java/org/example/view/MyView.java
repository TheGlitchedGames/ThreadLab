package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MyView extends JFrame {
    private ConfigurationPanel configPanel;
    private DataPanel dataPanel;
    private Viewer viewer;
    private ControlPanel controlPanel;

    // Paleta de colores para la aplicación
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 245);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color HEADER_COLOR = new Color(70, 130, 180);
    private static final Color TEXT_COLOR = new Color(50, 50, 50);

    public MyView() {
        setTitle("ThreadLab - Monitor de Recursos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Establecer look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // Configuración global de UI
            UIManager.put("Table.gridColor", new Color(230, 230, 230));
            UIManager.put("Table.selectionBackground", new Color(210, 230, 255));
            UIManager.put("Panel.background", PANEL_COLOR);
            UIManager.put("Label.foreground", TEXT_COLOR);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar apariencia de la ventana principal
        getContentPane().setBackground(BACKGROUND_COLOR);
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        initComponents();
        pack();
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre paneles

        // Panel de Configuración (Izquierda)
        configPanel = new ConfigurationPanel();
        configPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(HEADER_COLOR, 1),
                "Configuración",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Dialog", Font.BOLD, 12),
                HEADER_COLOR));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(configPanel, gbc);

        // Panel de Datos (Centro)
        dataPanel = new DataPanel();
        dataPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(HEADER_COLOR, 1),
                "Estado del Sistema",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Dialog", Font.BOLD, 12),
                HEADER_COLOR));
        gbc.gridx = 1;
        gbc.weightx = 0.25;
        add(dataPanel, gbc);

        // Panel de Visualización (Derecha)
        viewer = new Viewer();
        viewer.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(HEADER_COLOR, 1),
                "Monitoreo en Tiempo Real",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Dialog", Font.BOLD, 12),
                HEADER_COLOR));
        gbc.gridx = 2;
        gbc.weightx = 0.5;
        add(viewer, gbc);

        // Panel de Control (Inferior)
        controlPanel = new ControlPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(HEADER_COLOR, 1),
                "Controles",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Dialog", Font.BOLD, 12),
                HEADER_COLOR));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        add(controlPanel, gbc);
    }

    public ConfigurationPanel getConfigPanel() { return configPanel; }
    public DataPanel getDataPanel() { return dataPanel; }
    public Viewer getViewer() { return viewer; }
    public ControlPanel getControlPanel() { return controlPanel; }
}