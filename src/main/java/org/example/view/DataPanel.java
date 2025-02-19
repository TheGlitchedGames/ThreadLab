package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataPanel extends JPanel {
    private JTable dataTable;

    public DataPanel() {
        setLayout(new BorderLayout());
        initTable();
    }

    private void initTable() {
        String[] columnNames = {"Data", "Values"};
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
        add(new JScrollPane(dataTable), BorderLayout.CENTER);
    }

    public JTable getDataTable() { return dataTable; }
}
