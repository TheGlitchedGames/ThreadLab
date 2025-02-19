package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class DataPanel extends JPanel {
    private JTable dataTable;

    public DataPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Statistics"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        initTable();
    }

    private void initTable() {
        String[] columnNames = {"Metric", "Value"};
        Object[][] data = {
                {"Total Resources", "0"},
                {"Total Producers", "0"},
                {"Total Consumers", "0"},
                {"Total Resources Quantity", "0"},
                {"Active Threads", "0"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dataTable = new JTable(model);
        dataTable.setRowHeight(30);
        dataTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Style the header
        JTableHeader header = dataTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.setBackground(new Color(230, 230, 230));
        header.setForeground(Color.BLACK);

        // Custom renderer for values
        dataTable.getColumnModel().getColumn(1).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getDataTable() { return dataTable; }
}