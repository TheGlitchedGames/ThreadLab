package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

public class Viewer extends JPanel {
    private JTable resourceTable;
    private JTable consumerTable;
    private JTable producerTable;

    public Viewer(MyModel model) {
        setLayout(new BorderLayout());

        String[] resourceColumns = {"Resource ID", "Quantity", "MinQ", "MaxQ", "Consumer num", "Producer num"};
        resourceTable = new JTable(new DefaultTableModel(null, resourceColumns));
        JScrollPane resourceScrollPane = new JScrollPane(resourceTable);

        String[] consumerColumns = {"Consumer ID", "Bound Resource", "Status", "Start Delay", "Consume Delay", "Times consumed", "Processing time", "Start time", "End time"};
        consumerTable = new JTable(new DefaultTableModel(null, consumerColumns));
        JScrollPane consumerScrollPane = new JScrollPane(consumerTable);

        String[] producerColumns = {"Producer ID", "Bound Resource", "Status", "Start Delay", "Produce Delay", "Times produced", "Processing time", "Start time", "End time"};
        producerTable = new JTable(new DefaultTableModel(null, producerColumns));
        JScrollPane producerScrollPane = new JScrollPane(producerTable);

        JPanel tablePanel = new JPanel(new GridLayout(3, 1));
        tablePanel.add(resourceScrollPane);
        tablePanel.add(consumerScrollPane);
        tablePanel.add(producerScrollPane);

        JComponent canvas = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(3));

                int width = getWidth();
                int height = getHeight();

                int topHeight = height / 4;
                int middleHeight = height / 3;
                int bottomHeight = height - (topHeight + middleHeight);

                g2.drawRect(5, 5, width - 10, topHeight - 10); // Tabla de Recursos
                g2.drawRect(5, topHeight + 5, width - 10, middleHeight - 10); // Tabla de Consumidores
                g2.drawRect(5, topHeight + middleHeight + 5, width - 10, bottomHeight - 10); // Tabla de Productores
            }
        };

        canvas.setOpaque(false);
        setLayout(new OverlayLayout(this));
        add(canvas);
        add(tablePanel);

        updateTables(model);
    }

    public void updateTables(MyModel model) {
        DefaultTableModel resourceModel = (DefaultTableModel) resourceTable.getModel();
        resourceModel.setRowCount(0);

        for (Resource resource : model.getResources()) {
            Object[] resourceInfo = model.getResourceInfo(resource);
            Object[] resourceRow = new Object[]{
                    resource.getId(),
                    resourceInfo[0],
                    resourceInfo[2],
                    resourceInfo[1],
                    model.getConsumerCount(resource),
                    model.getProducerCount(resource)
            };
            resourceModel.addRow(resourceRow);
        }

        DefaultTableModel consumerModel = (DefaultTableModel) consumerTable.getModel();
        consumerModel.setRowCount(0);
        for (Object[] row : model.getConsumerInfo()) {
            consumerModel.addRow(row);
        }

        DefaultTableModel producerModel = (DefaultTableModel) producerTable.getModel();
        producerModel.setRowCount(0);
        for (Object[] row : model.getProducerInfo()) {
            producerModel.addRow(row);
        }
    }
}