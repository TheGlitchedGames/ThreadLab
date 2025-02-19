package org.example;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private JTable configTable;
    private MyModel myModel;

    public ConfigurationPanel(MyModel myModel) {
        this.myModel = myModel;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Configuration Settings", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Parameter", "Value"};
        Object[][] data = {
                {"=== Resource Settings ===", ""},
                {"Number of Resources", "1"},
                {"Max Resource Quantity", "100"},
                {"Min Resource Quantity", "0"},
                {"=== Thread Settings ===", ""},
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
                if (e.getColumn() == 1) {
                    try {
                        String value = (String) model.getValueAt(e.getFirstRow(), 1);
                        int intValue = Integer.parseInt(value);
                        switch (e.getFirstRow()) {
                            case 1: // Number of Resources
                                myModel.setResourceCount(intValue);
                                break;
                            case 2: // Max Resource Quantity
                                myModel.setMaxQuantityForAllResources(intValue);
                                break;
                            case 3: // Min Resource Quantity
                                myModel.setMinQuantityForAllResources(intValue);
                                break;
                            case 5: // Number of Producers
                                myModel.setProducerCount(intValue);
                                break;
                            case 6: // Number of Consumers
                                myModel.setConsumerCount(intValue);
                                break;
                        }
                    } catch (NumberFormatException ex) {
                        model.setValueAt("0", e.getFirstRow(), 1);
                    }
                }
            }
        });

        configTable = new JTable(model);
        configTable.setRowHeight(30);
        configTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        configTable.getColumnModel().getColumn(1).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(configTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}