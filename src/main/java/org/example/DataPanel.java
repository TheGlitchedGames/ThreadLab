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
}