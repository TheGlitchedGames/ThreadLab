package org.example;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private JTable configTable;
    private Resource resource;
    private MyModel myModel;

    public ConfigurationPanel(MyModel myModel) {
        this.myModel = myModel;
        this.resource = myModel.getResource();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Configuration Settings", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Parameter", "Value"};
        Object[][] data = {
                {"== Resource Type Settings ==", ""},
                {"Total Resources", "1"},
                {"Max General Resources", "100"},
                {"Min General Resources", "0"},
                {"== Producer/Consumer Count ==", ""},
                {"Number of Producers", "0"},
                {"Number of Consumers", "0"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 && row != 0 && row != 4;
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
                        switch (row) {
                            case 2: // Max General Resources
                                resource.setMaxQuantity(intValue);
                                break;
                            case 3: // Min General Resources
                                resource.setMinQuantity(intValue);
                                break;
                            case 5: // Number of Producers
                                myModel.setProducerCount(intValue);
                                break;
                            case 6: // Number of Consumers
                                myModel.setConsumerCount(intValue);
                                break;
                        }
                    } catch (NumberFormatException ex) {
                        model.setValueAt("0", row, column);
                    }
                }
            }
        });


        configTable = new JTable(model);
        configTable.setTableHeader(null);
        configTable.setRowHeight(30);
        configTable.getColumnModel().getColumn(0).setCellRenderer(new MergedCellRenderer());

        JScrollPane scrollPane = new JScrollPane(configTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateResourceData() {
        DefaultTableModel model = (DefaultTableModel) configTable.getModel();
        Object[] resourceInfo = myModel.getResourceInfo();
        model.setValueAt(String.valueOf(resourceInfo[1]), 2, 1); // Max
        model.setValueAt(String.valueOf(resourceInfo[2]), 3, 1); // Min
        model.setValueAt(String.valueOf(myModel.getActiveThreadCount()/2), 5, 1); // Producers
        model.setValueAt(String.valueOf(myModel.getActiveThreadCount()/2), 6, 1); // Consumers
    }

    public Object[][] getConfigData() {
        DefaultTableModel model = (DefaultTableModel) configTable.getModel();
        Object[][] data = new Object[model.getRowCount()][2];
        for (int i = 0; i < model.getRowCount(); i++) {
            data[i][0] = model.getValueAt(i, 0);
            data[i][1] = model.getValueAt(i, 1);
        }
        return data;
    }

    private static class MergedCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row == 0 || row == 4) {
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                c.setFont(c.getFont().deriveFont(Font.BOLD));
            }
            return c;
        }
    }
}
