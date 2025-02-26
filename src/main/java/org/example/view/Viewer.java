package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.Font;

public class Viewer extends JPanel {
    private JTable resourceTable;
    private JTable consumersTable;
    private JTable producersTable;

    // Colores para los paneles
    private static final Color HEADER_BG = new Color(70, 130, 180);
    private static final Color HEADER_FG = Color.BLACK;
    private static final Color TABLE_STRIPE = new Color(245, 245, 250);

    public Viewer() {
        setLayout(new GridLayout(3, 1, 0, 10)); // Añadir espacio entre tablas
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);
        initTables();
    }

    private void initTables() {
        // Recursos Table
        String[] resourcesColumns = {"ID Recurso", "Cantidad", "Min Q", "Max Q"};
        resourceTable = createStyledTable(resourcesColumns);
        JPanel resourcesPanel = createTablePanel(resourceTable, "Recursos", new Color(100, 180, 100, 40));
        add(resourcesPanel);

        // Consumidores Table
        String[] consumersColumns = {"ID Consumidor", "Recurso", "Estado", "Veces Consumido", "Tiempo Inicio", "Tiempo Fin"};
        consumersTable = createStyledTable(consumersColumns);
        JPanel consumersPanel = createTablePanel(consumersTable, "Consumidores", new Color(180, 100, 100, 40));
        add(consumersPanel);

        // Productores Table
        String[] producersColumns = {"ID Productor", "Recurso", "Estado", "Veces Producido", "Tiempo Inicio", "Tiempo Fin"};
        producersTable = createStyledTable(producersColumns);
        JPanel producersPanel = createTablePanel(producersTable, "Productores", new Color(100, 100, 180, 40));
        add(producersPanel);
    }

    private JTable createStyledTable(String[] columns) {
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(10, 1));
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setFillsViewportHeight(true);

        // Estilo para el encabezado
        JTableHeader header = table.getTableHeader();
        header.setBackground(HEADER_BG);
        header.setForeground(HEADER_FG);
        header.setFont(new Font("Dialog", Font.BOLD, 12));

        // Filas alternadas
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : TABLE_STRIPE);
                }

                return c;
            }
        });

        return table;
    }

    private JPanel createTablePanel(JTable table, String title, Color accentColor) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(Color.WHITE);

        // Título estilizado
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        titleLabel.setForeground(HEADER_BG);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, HEADER_BG),
                new EmptyBorder(5, 10, 5, 10)
        ));

        // Panel con fondo de color
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(accentColor);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.add(new JScrollPane(table));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    public JTable getResourceTable() { return resourceTable; }
    public JTable getConsumersTable() { return consumersTable; }
    public JTable getProducersTable() { return producersTable; }
}