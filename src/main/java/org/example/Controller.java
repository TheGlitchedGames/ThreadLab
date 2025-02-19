package org.example;

import javax.swing.*;

public class Controller {
    private MyView myView;
    private MyModel myModel;
    private Timer updateTimer;
    private boolean isRunning = false;

    public Controller() {
        myModel = new MyModel();
        myView = new MyView(this, myModel);

        // Create timer for periodic updates (every 100ms)
        updateTimer = new Timer(100, e -> update());
    }

    private void update() {
        if (isRunning) {
            myView.updateData();
        }
    }

    public void play() {
        if (!isRunning) {
            isRunning = true;
            myModel.start();
            updateTimer.start();
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            myModel.stop();
            updateTimer.stop();
        }
    }

    // Método para verificar si la simulación está en ejecución
    public boolean isRunning() {
        return isRunning;
    }
}