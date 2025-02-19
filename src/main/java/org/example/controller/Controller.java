package org.example.controller;

import org.example.model.MyModel;
import org.example.model.dto.ConsumerDTO;
import org.example.model.dto.ProducerDTO;
import org.example.model.dto.ResourceDTO;
import org.example.view.MyView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private MyModel model;
    private MyView view;
    private Timer updateTimer;

    public Controller(MyModel model, MyView view) {
        this.model = model;
        this.view = view;
        initializeControllers();
    }

    private void initializeControllers() {
        view.getControlPanel().getPlayButton().addActionListener(e -> startSimulation());
        view.getControlPanel().getStopButton().addActionListener(e -> stopSimulation());
    }

    private void startSimulation() {
        Map<String, Integer> config = readConfiguration();
        if (config != null) {
            model.play(config);
            startUpdateTimer();
            view.getControlPanel().getPlayButton().setEnabled(false);
            view.getControlPanel().getStopButton().setEnabled(true);
        }
    }

    private void stopSimulation() {
        model.stop();
        if (updateTimer != null) {
            updateTimer.cancel();
            updateTimer = null;
        }
        view.getControlPanel().getPlayButton().setEnabled(true);
        view.getControlPanel().getStopButton().setEnabled(false);
    }

    private Map<String, Integer> readConfiguration() {
        try {
            Map<String, Integer> config = new HashMap<>();
            JTable configTable = view.getConfigPanel().getConfigTable();

            config.put("Number of Resources",
                    Integer.parseInt(configTable.getValueAt(1, 1).toString()));
            config.put("Max Resources Quantity",
                    Integer.parseInt(configTable.getValueAt(2, 1).toString()));
            config.put("Min Resource Quantity",
                    Integer.parseInt(configTable.getValueAt(3, 1).toString()));
            config.put("Number of Producers",
                    Integer.parseInt(configTable.getValueAt(5, 1).toString()));
            config.put("Number of Consumers",
                    Integer.parseInt(configTable.getValueAt(6, 1).toString()));

            return config;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Please enter valid numbers in all fields");
            return null;
        }
    }

    private void updateDataPanel() {
        DefaultTableModel dataModel = (DefaultTableModel) view.getDataPanel().getDataTable().getModel();
        dataModel.setValueAt(model.getResourceInfo().size(), 0, 1); // Total Resources
        dataModel.setValueAt(model.getProducerInfo().size(), 1, 1); // Total Producers
        dataModel.setValueAt(model.getConsumerInfo().size(), 2, 1); // Total Consumers
        dataModel.setValueAt(model.getTotalResourcesQuantity(), 3, 1); // Total Resources Quantity
        dataModel.setValueAt(model.getActiveThreads(), 4, 1); // Active Threads
    }

    private void updateViewerTables() {
        // Update Resources table
        DefaultTableModel resourceModel = (DefaultTableModel) view.getViewer().getResourceTable().getModel();
        resourceModel.setRowCount(0);
        for (ResourceDTO resource : model.getResourceInfo()) {
            resourceModel.addRow(new Object[]{
                    resource.getId(),
                    resource.getQuantity(),
                    resource.getMinQuantity(),
                    resource.getMaxQuantity()
            });
        }

        // Update Producers table
        DefaultTableModel producerModel = (DefaultTableModel) view.getViewer().getProducersTable().getModel();
        producerModel.setRowCount(0);
        for (ProducerDTO producer : model.getProducerInfo()) {
            producerModel.addRow(new Object[]{
                    producer.getId(),
                    producer.getResourceId(),
                    producer.getStatus(),
                    producer.getTimesProduced(),
                    producer.getStartTime(),
                    producer.getEndTime()
            });
        }

        // Update Consumers table
        DefaultTableModel consumerModel = (DefaultTableModel) view.getViewer().getConsumersTable().getModel();
        consumerModel.setRowCount(0);
        for (ConsumerDTO consumer : model.getConsumerInfo()) {
            consumerModel.addRow(new Object[]{
                    consumer.getId(),
                    consumer.getResourceId(),
                    consumer.getStatus(),
                    consumer.getTimesConsumed(),
                    consumer.getStartTime(),
                    consumer.getEndTime()
            });
        }
    }

    private void startUpdateTimer() {
        updateTimer = new Timer();
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    updateDataPanel();
                    updateViewerTables();
                });
            }
        }, 0, 1000);
    }
}