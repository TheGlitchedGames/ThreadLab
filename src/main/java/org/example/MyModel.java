package org.example;

import java.util.ArrayList;
import java.util.List;

public class MyModel {
    private final Resource resource;
    private final List<Producer> producers;
    private final List<Consumer> consumers;

    public MyModel() {
        this.resource = new Resource(100, 0);
        this.producers = new ArrayList<>();
        this.consumers = new ArrayList<>();
    }

    public Resource getResource() {
        return resource;
    }

    public List<Object[]> getConsumerInfo() {
        List<Object[]> info = new ArrayList<>();
        for (Consumer consumer : consumers) {
            info.add(new Object[]{consumer.getState(),
                    consumer.getStartTime(), consumer.getStopTime()});
        }
        return info;
    }

    public List<Object[]> getProducerInfo() {
        List<Object[]> info = new ArrayList<>();
        for (Producer producer : producers) {
            info.add(new Object[]{producer.getState(),
                    producer.getStartTime(), producer.getStopTime()});
        }
        return info;
    }

    public Object[] getResourceInfo() {
        return new Object[]{resource.getQuantity(), resource.getMaxQuantity()
                , resource.getMinQuantity()};
    }

    public void start() {
        for (Producer producer: producers) {
            new Thread(producer).start();
        }
        for (Consumer consumer : consumers) {
            new Thread(consumer).start();
        }
    }

    public void stop() {
        for (Producer producer : producers) {
            producer.stop();
        }
        for (Consumer consumer : consumers) {
            consumer.stop();
        }
    }

    public void addProducer(Producer producer) {
        producers.add(producer);
    }

    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }
}