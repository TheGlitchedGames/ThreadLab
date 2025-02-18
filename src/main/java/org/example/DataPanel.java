package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataPanel extends JPanel {
    private JTable dataTable;

    public DataPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Data", "Values"};
        Object[][] data = {
                {"Total Resources", "1"},
                {"Total Producers", "0"},
                {"Total Consumers", "0"},
                {"Total resources quantity", "0"},
                {"Active Threads", "0"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        dataTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateData(MyModel model) {
        DefaultTableModel tableModel = (DefaultTableModel) dataTable.getModel();
        tableModel.setValueAt("1", 0, 1);
        tableModel.setValueAt(String.valueOf(model.getProducerCount()), 1, 1); // Producers
        tableModel.setValueAt(String.valueOf(model.getConsumerCount()), 2, 1); // Consumers
        tableModel.setValueAt(String.valueOf(model.getTotalResourcesQuantity()), 3, 1); // Resource quantity
        tableModel.setValueAt(String.valueOf(model.getActiveThreadCount()), 4, 1); // Active threads
    }
}
