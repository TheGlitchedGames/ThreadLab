package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Viewer extends JPanel {
    private JTable resourceTable;
    private JTable consumersTable;
    private JTable producersTable;

    public Viewer() {
        setLayout(new GridLayout(3, 1));
        initTables();
    }

    private void initTables() {
        // Resources Table
        String[] resourcesColumns = {"ResourceID", "Quantity", "MinQ", "MaxQ"};
        resourceTable = new JTable(new DefaultTableModel(resourcesColumns, 0));
        JPanel resourcesPanel = new JPanel(new BorderLayout());
        resourcesPanel.add(new JLabel("Resources"), BorderLayout.NORTH);
        resourcesPanel.add(new JScrollPane(resourceTable), BorderLayout.CENTER);
        add(resourcesPanel);

        // Consumers Table
        String[] consumersColumns = {"ConsumerID", "Resource", "Status", "Times Consumed", "Start Time", "End Time"};
        consumersTable = new JTable(new DefaultTableModel(consumersColumns, 0));
        JPanel consumersPanel = new JPanel(new BorderLayout());
        consumersPanel.add(new JLabel("Consumers"), BorderLayout.NORTH);
        consumersPanel.add(new JScrollPane(consumersTable), BorderLayout.CENTER);
        add(consumersPanel);

        // Producers Table
        String[] producersColumns = {"ProducerID", "Resource", "Status", "Times Produced", "Start Time", "End Time"};
        producersTable = new JTable(new DefaultTableModel(producersColumns, 0));
        JPanel producersPanel = new JPanel(new BorderLayout());
        producersPanel.add(new JLabel("Producers"), BorderLayout.NORTH);
        producersPanel.add(new JScrollPane(producersTable), BorderLayout.CENTER);
        add(producersPanel);
    }

    public JTable getResourceTable() { return resourceTable; }
    public JTable getConsumersTable() { return consumersTable; }
    public JTable getProducersTable() { return producersTable; }
}
