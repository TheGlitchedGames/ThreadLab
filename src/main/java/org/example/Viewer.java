package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Viewer extends JPanel {
    private JTable resourceTable;
    private JTable consumerTable;
    private JTable producerTable;
    private DefaultTableModel resourceModel;
    private DefaultTableModel consumerModel;
    private DefaultTableModel producerModel;

    public Viewer(MyModel model) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Resource Table
        String[] resourceColumns = {"Resource ID", "Quantity", "MinQ", "MaxQ"};
        resourceModel = new DefaultTableModel(resourceColumns, 0);
        resourceTable = new JTable(resourceModel);
        JScrollPane resourcePane = new JScrollPane(resourceTable);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.33;
        add(createTitledPanel("Resources", resourcePane), gbc);

        // Consumer Table
        String[] consumerColumns = {"Consumer ID", "Resource", "Status", "Times Consumed", "Start Time", "End Time"};
        consumerModel = new DefaultTableModel(consumerColumns, 0);
        consumerTable = new JTable(consumerModel);
        JScrollPane consumerPane = new JScrollPane(consumerTable);

        gbc.gridy = 1;
        add(createTitledPanel("Consumers", consumerPane), gbc);

        // Producer Table
        String[] producerColumns = {"Producer ID", "Resource", "Status", "Times Produced", "Start Time", "End Time"};
        producerModel = new DefaultTableModel(producerColumns, 0);
        producerTable = new JTable(producerModel);
        JScrollPane producerPane = new JScrollPane(producerTable);

        gbc.gridy = 2;
        add(createTitledPanel("Producers", producerPane), gbc);
    }

    private JPanel createTitledPanel(String title, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    public void updateTables(MyModel model) {
        // Update Resource Table
        resourceModel.setRowCount(0);
        for (Resource resource : model.getResources()) {
            resourceModel.addRow(new Object[]{
                    resource.getId(),
                    resource.getQuantity(),
                    resource.getMinQuantity(),
                    resource.getMaxQuantity()
            });
        }

        // Add new rows for Consumer activities
        for (Object[] row : model.getConsumerInfo()) {
            consumerModel.addRow(new Object[]{
                    row[0], // Consumer ID
                    row[1], // Resource ID
                    row[2], // Status
                    row[5], // Times Consumed
                    row[7], // Start Time
                    row[8]  // End Time
            });
        }

        // Add new rows for Producer activities
        for (Object[] row : model.getProducerInfo()) {
            producerModel.addRow(new Object[]{
                    row[0], // Producer ID
                    row[1], // Resource ID
                    row[2], // Status
                    row[5], // Times Produced
                    row[7], // Start Time
                    row[8]  // End Time
            });
        }
    }
}