package org.example;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Controller {
    private MyView myView;
    private MyModel myModel;
    private Timer updateTimer;

    public Controller() {
        myModel = new MyModel();
        myView = new MyView(myModel.getResource(), this, myModel);

        updateTimer = new Timer(100, e -> update());
    }

    private void update() {
        myView.updateData();
    }

    public void play() {
        myModel.start();
        updateTimer.start();
    }

    public void stop() {
        myModel.stop();
        updateTimer.stop();
    }
}
