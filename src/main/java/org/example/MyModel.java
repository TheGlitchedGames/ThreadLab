package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyModel {
    private final List<Resource> resources;
    private final Map<Resource, List<Producer>> producerMap;
    private final Map<Resource, List<Consumer>> consumerMap;

    public MyModel() {
        this.resources = new ArrayList<>();
        this.producerMap = new HashMap<>();
        this.consumerMap = new HashMap<>();
    }

    public void addResource(String id, int maxQuantity, int minQuantity) {
        Resource resource = new Resource(id, maxQuantity, minQuantity);
        resources.add(resource);
        producerMap.put(resource, new ArrayList<>());
        consumerMap.put(resource, new ArrayList<>());
    }

    public Resource getResource() {
        return resource;
    }

    public List<Object[]> getConsumerInfo() {
        List<Object[]> info = new ArrayList<>();
        for (Consumer consumer : consumers) {
            long processingTime = consumer.getStartTime() != null ?
                    System.currentTimeMillis() - Long.parseLong(consumer.getStartTime().replace(":", "")) : 0;

            info.add(new Object[] {
                    consumer.hashCode(),
                    resource.hashCode(),
                    consumer.getState(),
                    100,
                    100,
                    consumer.getTimesConsumed(),
                    processingTime,
                    consumer.getStartTime(),
                    consumer.getStopTime()
            });
        }
        return info;
    }

    public List<Object[]> getProducerInfo() {
        List<Object[]> info = new ArrayList<>();
        for (Producer producer : producers) {
            long processingTime = producer.getStartTime() != null ?
                    System.currentTimeMillis() - Long.parseLong(producer.getStartTime().replace(":", "")) : 0;

            info.add(new Object[]{
                    producer.hashCode(),
                    resource.hashCode(),
                    producer.getState(),
                    100,
                    100,
                    producer.getTimesProduced(),
                    processingTime,
                    producer.getStartTime(),
                    producer.getStopTime()
            });
        }
        return info;
    }


    public Object[] getResourceInfo() {
        return new Object[]{resource.getQuantity(), resource.getMaxQuantity(), resource.getMinQuantity()};
    }

    public void setProducerCount(int count) {
        while (producers.size() < count) {
            addProducer(new Producer(resource));
        }
        while (producers.size() > count) {
            producers.remove(producers.size() - 1);
        }
    }

    public void setConsumerCount(int count) {
        while (consumers.size() < count) {
            addConsumer(new Consumer(resource));
        }
        while (consumers.size() > count) {
            consumers.remove(consumers.size() - 1);
        }
    }

    public int getTotalResourcesQuantity() {
        return resource.getQuantity();
    }

    public int getActiveThreadCount() {
        return producers.size() + consumers.size();
    }

    public void start() {
        for (Producer producer : producers) {
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

    public void initializeSimulation() {
        producers.clear();
        consumers.clear();

        for (int i = 0; i < producerCount; i++) {
            Producer producer = new Producer(resource);
            producers.add(producer);
        }

        for (int i = 0; i < consumerCount; i++) {
            Consumer consumer = new Consumer(resource);
            consumers.add(consumer);
        }
    }

    public int getProducerCount() {
        return producers.size();
    }

    public int getConsumerCount() {
        return consumers.size();
    }
}
