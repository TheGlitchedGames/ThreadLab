package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class DataPanel extends JPanel {
    private JTable dataTable;
    private JLabel statusLabel;

    // Colores para la tabla
    private static final Color HIGHLIGHT_COLOR = new Color(240, 248, 255);
    private static final Color LABEL_COLOR = new Color(70, 130, 180);
    private static final Color VALUE_COLOR = new Color(50, 50, 50);

    public DataPanel() {
        setLayout(new BorderLayout(0, 10));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);
        initComponents();
    }

    private void initComponents() {
        // Panel superior con estado actual
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(Color.WHITE);
        statusPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                new EmptyBorder(5, 5, 5, 5)
        ));

        JLabel statusTitle = new JLabel("Estado: ");
        statusTitle.setFont(new Font("Dialog", Font.BOLD, 14));
        statusTitle.setForeground(LABEL_COLOR);

        statusLabel = new JLabel("Esperando Inicio");
        statusLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        statusLabel.setForeground(Color.ORANGE);

        statusPanel.add(statusTitle, BorderLayout.WEST);
        statusPanel.add(statusLabel, BorderLayout.CENTER);

        add(statusPanel, BorderLayout.NORTH);

        // Inicializar la tabla
        initTable();

        // Añadir gráfico resumen en la parte inferior
        JPanel summaryPanel = createSummaryPanel();
        add(summaryPanel, BorderLayout.SOUTH);
    }

    private void initTable() {
        String[] columnNames = {"Dato", "Valor"};
        Object[][] data = {
                {"Total de Recursos", "0"},
                {"Total de Productores", "0"},
                {"Total de Consumidores", "0"},
                {"Cantidad Total de Recursos", "0"},
                {"Hilos Activos", "0"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dataTable = new JTable(model);
        dataTable.setRowHeight(30);
        dataTable.setShowGrid(false);
        dataTable.setIntercellSpacing(new Dimension(0, 0));

        // Personalizando la apariencia de la tabla
        dataTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JLabel cell = new JLabel(value.toString());
                cell.setOpaque(true);

                // Alternar colores de fondo para filas
                if (row % 2 == 0) {
                    cell.setBackground(HIGHLIGHT_COLOR);
                } else {
                    cell.setBackground(Color.WHITE);
                }

                // Estilo para etiquetas y valores
                if (column == 0) {
                    cell.setFont(new Font("Dialog", Font.BOLD, 12));
                    cell.setForeground(LABEL_COLOR);
                    cell.setBorder(new EmptyBorder(0, 10, 0, 0));
                } else {
                    cell.setFont(new Font("Dialog", Font.PLAIN, 12));
                    cell.setForeground(VALUE_COLOR);
                    cell.setHorizontalAlignment(SwingConstants.RIGHT);
                    cell.setBorder(new EmptyBorder(0, 0, 0, 10));
                }

                return cell;
            }
        });

        // Ocultar encabezados de tabla ya que no son necesarios
        dataTable.setTableHeader(null);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(LABEL_COLOR),
                "Uso de Recursos",
                1,
                1,
                new Font("Dialog", Font.BOLD, 12),
                LABEL_COLOR));

        // Simulación de gráfico de recursos (podría reemplazarse con un gráfico real)
        JPanel barGraph = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                // Dibujar fondo
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, width, height);

                // Dibujar ejes
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawLine(30, height - 20, width - 10, height - 20); // Eje X
                g2d.drawLine(30, 10, 30, height - 20); // Eje Y

                // Etiquetas
                g2d.setFont(new Font("Dialog", Font.PLAIN, 10));
                g2d.drawString("0%", 10, height - 15);
                g2d.drawString("100%", 5, 15);

                // Barras de ejemplo (estas serían reemplazadas con datos reales)
                g2d.setColor(new Color(70, 130, 180, 200));
                int barWidth = 40;
                int spacing = 20;
                int startX = 50;

                // Barra 1
                g2d.fillRect(startX, height - 70, barWidth, 50);
                g2d.drawString("Rec 1", startX + 5, height - 10);

                // Barra 2
                g2d.fillRect(startX + barWidth + spacing, height - 40, barWidth, 20);
                g2d.drawString("Rec 2", startX + barWidth + spacing + 5, height - 10);

                // Barra 3
                g2d.fillRect(startX + (barWidth + spacing) * 2, height - 90, barWidth, 70);
                g2d.drawString("Rec 3", startX + (barWidth + spacing) * 2 + 5, height - 10);
            }
        };

        barGraph.setPreferredSize(new Dimension(200, 100));
        panel.add(barGraph);

        return panel;
    }

    // Método para actualizar el estado
    public void updateStatus(String status, Color color) {
        statusLabel.setText(status);
        statusLabel.setForeground(color);
    }

    public JTable getDataTable() { return dataTable; }
}