package org.example;

public class Controller {
    private MyView myView;
    private MyModel myModel;

    public Controller() {
        myModel = new MyModel();
        myView = new MyView(myModel.getResource());
    }

    public void getModalInfo() {

    }

    public void play() {

    }

    public void stop() {

    }
}