package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private JTable configTable;

    public ConfigurationPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Configuration"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        initTable();
    }

    private void initTable() {
        String[] columnNames = {"Parameter", "Value"};
        Object[][] data = {
                {"===Resource Settings===", ""},
                {"Number of Resources", "3"},
                {"Max Resources Quantity", "10"},
                {"Min Resource Quantity", "1"},
                {"===Thread Settings===", ""},
                {"Number of Producers", "2"},
                {"Number of Consumers", "2"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 && row != 0 && row != 4;
            }
        };

        configTable = new JTable(model);
        configTable.setRowHeight(30);
        configTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Style the header
        JTableHeader header = configTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.setBackground(new Color(230, 230, 230));
        header.setForeground(Color.BLACK);

        // Style category rows
        configTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row == 0 || row == 4) {
                    c.setFont(new Font("SansSerif", Font.BOLD, 12));
                    c.setBackground(new Color(240, 240, 240));
                    c.setForeground(new Color(70, 70, 70));
                } else {
                    c.setFont(new Font("SansSerif", Font.PLAIN, 12));
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(configTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getConfigTable() { return configTable; }
}