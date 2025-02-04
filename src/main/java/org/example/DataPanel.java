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
                {"Total Resources", ""},
                {"Total Producers", ""},
                {"Total Consumers", ""},
                {"Total resources quantity", ""},
                {"Active Threads", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        dataTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateData(Object[][] configData) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        for (Object[] row : configData) {
            String parameter = (String) row[0];
            String value = (String) row[1];
            switch (parameter) {
                case "Total Resources":
                    model.setValueAt(value, 0, 1);
                    break;
                case "Number of Producers":
                    model.setValueAt(value, 1, 1);
                    break;
                case "Number of Consumers":
                    model.setValueAt(value, 2, 1);
                    break;
            }
        }
    }
}