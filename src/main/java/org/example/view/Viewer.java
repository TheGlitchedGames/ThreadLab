package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Viewer extends JPanel {
    private JTable resourceTable;
    private JTable consumersTable;
    private JTable producersTable;

    public Viewer() {
        setLayout(new GridLayout(3, 1, 0, 10));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        initTables();
    }

    private void initTables() {
        // Resources Table
        String[] resourcesColumns = {"Resource ID", "Quantity", "Min Q", "Max Q"};
        resourceTable = createStyledTable(resourcesColumns);
        JPanel resourcesPanel = createTablePanel(resourceTable, "Resources");
        add(resourcesPanel);

        // Consumers Table
        String[] consumersColumns = {"Consumer ID", "Resource", "Status", "Times Consumed", "Start Time", "End Time"};
        consumersTable = createStyledTable(consumersColumns);
        JPanel consumersPanel = createTablePanel(consumersTable, "Consumers");
        add(consumersPanel);

        // Producers Table
        String[] producersColumns = {"Producer ID", "Resource", "Status", "Times Produced", "Start Time", "End Time"};
        producersTable = createStyledTable(producersColumns);
        JPanel producersPanel = createTablePanel(producersTable, "Producers");
        add(producersPanel);
    }

    private JTable createStyledTable(String[] columns) {
        JTable table = new JTable(new DefaultTableModel(columns, 0));
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Style the header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.setBackground(new Color(230, 230, 230));
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);

        return table;
    }

    private JPanel createTablePanel(JTable table, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(title),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public JTable getResourceTable() { return resourceTable; }
    public JTable getConsumersTable() { return consumersTable; }
    public JTable getProducersTable() { return producersTable; }
}