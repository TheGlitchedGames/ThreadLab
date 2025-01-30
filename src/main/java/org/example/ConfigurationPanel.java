package org.example;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private JTable configTable;
    private Resource resource;

    public ConfigurationPanel(Resource resource) {
        this.resource = resource;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Configuration Settings", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Parameter", "Value"};
        Object[][] data = {
                {"== Resource Type Settings ==", ""},
                {"Total Resources", ""},
                {"Max General Resources", ""},
                {"Min General Resources", ""},
                {"== Producer/Consumer Count ==", ""},
                {"Number of Producers", ""},
                {"Number of Consumers", ""},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 1) {
                    String value = (String) model.getValueAt(row, column);
                    try {
                        int intValue = Integer.parseInt(value);
                        if (row == 2) {
                            resource.setMaxQuantity(intValue);
                        } else if (row == 3) {
                            resource.setMinQuantity(intValue);
                        }
                    } catch (NumberFormatException ex) {
                        // Handle invalid number format
                    }
                }
            }
        });

        configTable = new JTable(model) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 0 && (row == 0 || row == 4)) {
                    return new BoldRenderer();
                }
                return super.getCellRenderer(row, column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };

        configTable.setTableHeader(null);
        configTable.setRowHeight(30);
        configTable.getColumnModel().getColumn(0).setCellRenderer(new MergedCellRenderer());

        JScrollPane scrollPane = new JScrollPane(configTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private static class BoldRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setFont(c.getFont().deriveFont(Font.BOLD));
            return c;
        }
    }

    private static class MergedCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row == 0 || row == 4) {
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                table.getColumnModel().getColumn(1).setMinWidth(0);
                table.getColumnModel().getColumn(1).setMaxWidth(0);
            }
            return c;
        }
    }
}