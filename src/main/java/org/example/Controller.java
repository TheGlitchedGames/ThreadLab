package org.example;

import java.util.List;

public class Controller {
    private MyView myView;
    private MyModel myModel;

    public Controller() {
        myModel = new MyModel();
        myView = new MyView(myModel.getResource(), this, myModel);

        myModel.addProducer(new Producer(myModel.getResource()));
        myModel.addConsumer(new Consumer(myModel.getResource()));
    }

    public void getModalInfo() {
        List<Object[]> consumerInfo = myModel.getConsumerInfo();
        List<Object[]> producerInfo = myModel.getProducerInfo();
        Object[] resourceInfo = myModel.getResourceInfo();
    }

    public void play() {
        myModel.start();
    }

    public void stop() {
        myModel.stop();
    }

    public void update() {
        myView.updateData();
    }
}