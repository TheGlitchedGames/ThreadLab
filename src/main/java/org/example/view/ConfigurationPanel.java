package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private JTable configTable;

    public ConfigurationPanel() {
        setLayout(new BorderLayout());
        initTable();
    }

    private void initTable() {
        String [] columnNames = {"Parameter", "Value"};
        Object[][] data = {
                {"===Resource Settings===", ""},
                {"Number of Resources", ""},
                {"Max Resources Quantity", ""},
                {"Min Resource Quantity", ""},
                {"===Thread Settings===", ""},
                {"Number of Producers", ""},
                {"Number of Consumers", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };

        configTable = new JTable(model);
        add(new JScrollPane(configTable), BorderLayout.CENTER);
    }

    public JTable getConfigTable() { return configTable; }
}
