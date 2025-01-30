package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Viewer extends JPanel {
    public Viewer() {
        setLayout(new BorderLayout());

        String[] resourceColumns = {"Resource ID", "Quantity", "MinQ", "MaxQ",
                "Consumer num", "Producer num"};
        JTable resourceTable = new JTable(new DefaultTableModel(null, resourceColumns));
        JScrollPane resourceScrollPane = new JScrollPane(resourceTable);

        String[] consumerColumns = {"Consumer ID", "Bound Resource", "Status",
                "Start Delay", "Consume Delay", "Times consumed",
                "Processing time", "Start time", "End time"};
        JTable consumerTable = new JTable(new DefaultTableModel(null, consumerColumns));
        JScrollPane consumerScrollPane = new JScrollPane(consumerTable);

        String[] producerColumns = {"Producer ID", "Bound Resource", "Status",
                "Start Delay", "Produce Delay", "Times produced",
                "Processing time", "Start time", "End time"};
        JTable producerTable = new JTable(new DefaultTableModel(null, producerColumns));
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
    }
}