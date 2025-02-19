package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataPanel extends JPanel {
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public DataPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Data", "Values"};
        Object[][] data = {
                {"Total Resources", "0"},
                {"Total Producers", "0"},
                {"Total Consumers", "0"},
                {"Total Resource Units", "0"},
                {"Active Threads", "0"}
        };

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Solo la columna de valores es editable
            }
        };

        dataTable = new JTable(tableModel);
        dataTable.setFont(new Font("Arial", Font.PLAIN, 14));
        dataTable.setRowHeight(25);

        // Hacer que la primera columna no sea editable
        dataTable.getColumnModel().getColumn(0).setCellEditor(null);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);

        // Añadir título
        JLabel titleLabel = new JLabel("Simulation Statistics", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
    }

    public void updateData(MyModel model) {
        // Actualizar cada fila con los valores actuales del modelo
        tableModel.setValueAt(String.valueOf(model.getResources().size()), 0, 1);
        tableModel.setValueAt(String.valueOf(model.getProducerCount()), 1, 1);
        tableModel.setValueAt(String.valueOf(model.getConsumerCount()), 2, 1);
        tableModel.setValueAt(String.valueOf(model.getTotalResourcesQuantity()), 3, 1);

        // Calcular threads activos (productores y consumidores que están en estado "Running")
        int activeThreads = model.getActiveThreadCount();
        tableModel.setValueAt(String.valueOf(activeThreads), 4, 1);
    }
}