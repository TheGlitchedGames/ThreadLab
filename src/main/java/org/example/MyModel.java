package org.example;

import java.util.*;

public class MyModel {
    private final List<Resource> resources;
    private final List<Producer> producers;
    private final List<Consumer> consumers;
    private final Random random;

    public MyModel() {
        this.resources = new ArrayList<>();
        this.producers = new ArrayList<>();
        this.consumers = new ArrayList<>();
        this.random = new Random();
        addResource("Resource-1", 100, 0); // Default resource
    }

    public void setResourceCount(int count) {
        while (resources.size() < count) {
            addResource("Resource-" + (resources.size() + 1), 100, 0);
        }
        while (resources.size() > count) {
            resources.remove(resources.size() - 1);
        }
    }

    public void setMaxQuantityForAllResources(int maxQuantity) {
        for (Resource resource : resources) {
            resource.setMaxQuantity(maxQuantity);
        }
    }

    public void setMinQuantityForAllResources(int minQuantity) {
        for (Resource resource : resources) {
            resource.setMinQuantity(minQuantity);
        }
    }

    public void addResource(String id, int maxQuantity, int minQuantity) {
        resources.add(new Resource(id, maxQuantity, minQuantity));
    }

    public void setProducerCount(int count) {
        while (producers.size() < count) {
            Resource randomResource = resources.get(random.nextInt(resources.size()));
            producers.add(new Producer(randomResource));
        }
        while (producers.size() > count) {
            producers.remove(producers.size() - 1);
        }
    }

    public void setConsumerCount(int count) {
        while (consumers.size() < count) {
            Resource randomResource = resources.get(random.nextInt(resources.size()));
            Consumer consumer = new Consumer(randomResource);
            consumers.add(consumer);
            new Thread(consumer).start();
        }
        while (consumers.size() > count) {
            Consumer consumer = consumers.remove(consumers.size() - 1);
            consumer.stop();  // Asegurar que los consumidores detenidos no sigan ejecutándose en segundo plano
        }
    }


    public List<Object[]> getConsumerInfo() {
        List<Object[]> info = new ArrayList<>();
        for (Consumer consumer : consumers) {
            info.add(new Object[]{
                    consumer.hashCode(),
                    consumer.getResource().getId(),
                    consumer.getState(),
                    100,
                    100,
                    consumer.getTimesConsumed(),
                    0,
                    consumer.getStartTime(),
                    consumer.getStopTime()
            });
        }
        return info;
    }

    public List<Object[]> getProducerInfo() {
        List<Object[]> info = new ArrayList<>();
        for (Producer producer : producers) {
            info.add(new Object[]{
                    producer.hashCode(),
                    producer.getResource().getId(),
                    producer.getState(),
                    100,
                    100,
                    producer.getTimesProduced(),
                    0,
                    producer.getStartTime(),
                    producer.getStopTime()
            });
        }
        return info;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public int getProducerCount() {
        return producers.size();
    }

    public int getConsumerCount() {
        return consumers.size();
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

    public int getTotalResourcesQuantity() {
        int total = 0;
        for (Resource resource : resources) {
            total += resource.getQuantity();
        }
        return total;
    }

    public int getActiveThreadCount() {
        return producers.size() + consumers.size();
    }
}