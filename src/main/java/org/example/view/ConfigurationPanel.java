package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private JTable configTable;

    // Colores para la tabla
    private static final Color HEADER_COLOR = new Color(80, 80, 80);
    private static final Color SECTION_BG = new Color(230, 230, 250);
    private static final Color EDITABLE_BG = new Color(255, 255, 220);

    public ConfigurationPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);
        initTable();
    }

    private void initTable() {
        String[] columnNames = {"Parámetro", "Valor"};
        Object[][] data = {
                {"===Configuración de Recursos===", ""},
                {"Número de Recursos", "3"},
                {"Cantidad Máxima de Recursos", "100"},
                {"Cantidad Mínima de Recursos", "10"},
                {"===Configuración de Hilos===", ""},
                {"Número de Productores", "5"},
                {"Número de Consumidores", "5"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo la columna de valores es editable y solo si no es un encabezado
                return column == 1 && !data[row][0].toString().startsWith("===");
            }
        };

        configTable = new JTable(model);
        configTable.setRowHeight(28);
        configTable.setIntercellSpacing(new Dimension(10, 2));

        // Personalización del renderizado de celdas
        configTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private final JLabel headerLabel = createStyledLabel(true);
            private final JLabel normalLabel = createStyledLabel(false);
            private final JTextField editField = new JTextField();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int col) {
                String text = value == null ? "" : value.toString();

                // Si es un encabezado de sección
                if (col == 0 && text.startsWith("===")) {
                    headerLabel.setText(text.replace("===", "").replace("===", ""));
                    headerLabel.setBackground(SECTION_BG);
                    return headerLabel;
                }
                // Si es una celda editable
                else if (col == 1 && model.isCellEditable(row, col)) {
                    editField.setText(text);
                    editField.setBackground(EDITABLE_BG);
                    editField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    return editField;
                }
                // Para el resto de celdas
                else {
                    normalLabel.setText(text);
                    normalLabel.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                    return normalLabel;
                }
            }

            private JLabel createStyledLabel(boolean isHeader) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                if (isHeader) {
                    label.setFont(new Font("Dialog", Font.BOLD, 12));
                    label.setForeground(HEADER_COLOR);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    label.setFont(new Font("Dialog", Font.PLAIN, 12));
                    label.setBorder(new EmptyBorder(0, 5, 0, 0));
                }
                return label;
            }
        });

        // Añadir título al panel
        JLabel titleLabel = new JLabel("Parámetros de Configuración", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        titleLabel.setForeground(HEADER_COLOR);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(configTable), BorderLayout.CENTER);

        // Añadir panel de ayuda
        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(new Color(240, 240, 240));
        helpPanel.setBorder(BorderFactory.createTitledBorder("Ayuda"));
        helpPanel.setLayout(new BorderLayout());

        JTextArea helpText = new JTextArea(
                "Establezca los valores en la tabla para\n" +
                        "configurar los recursos y los hilos.\n" +
                        "Haga clic en 'Iniciar Simulación' cuando\n" +
                        "esté listo para comenzar."
        );
        helpText.setEditable(false);
        helpText.setBackground(helpPanel.getBackground());
        helpText.setFont(new Font("Dialog", Font.PLAIN, 11));
        helpPanel.add(helpText, BorderLayout.CENTER);

        add(helpPanel, BorderLayout.SOUTH);
    }

    public JTable getConfigTable() { return configTable; }
}