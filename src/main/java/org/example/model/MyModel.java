package org.example.model;

import org.example.model.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyModel {
    private List<Resources> resources;
    private List<Producer> producers;
    private List<Consumer> consumers;
    private List<Thread> threads;
    private volatile boolean isRunning;

    public MyModel() {
        resources = new ArrayList<>();
        producers = new ArrayList<>();
        consumers = new ArrayList<>();
        threads = new ArrayList<>();
        isRunning = false;
    }

    public void play(Map<String, Integer> config) {
        if (isRunning) return;

        //Clear previous simulation data
        stop();
        resources.clear();
        producers.clear();
        consumers.clear();
        threads.clear();

        //Create resources
        int numResources = config.get("Number of Resources");
        int maxQuantity = config.get("Max Resources Quantity");
        int minQuantity = config.get("Min Resource Quantity");

        for (int i = 0; i < numResources; i++) {
            resources.add(new Resources(i, maxQuantity, minQuantity));
        }

        //Create producers and consumers
        Random random = new Random();
        int numProducers = config.get("Number of Producers");
        int numConsumers = config.get("Number of Consumers");

        for (int i = 0; i < numProducers; i++) {
            Resources resource = resources.get(random.nextInt(resources.size()));
            Producer producer = new Producer(i, resource);
            producers.add(producer);
            threads.add(new Thread(producer));
        }

        for (int i = 0; i < numConsumers; i++) {
            Resources resource = resources.get(random.nextInt(resources.size()));
            Consumer consumer = new Consumer(i, resource);
            consumers.add(consumer);
            threads.add(new Thread(consumer));
        }

        //Start threads
        isRunning = true;
        threads.forEach(Thread::start);
    }

    public void stop() {
        isRunning = false;
        producers.forEach(Producer::stop);
        consumers.forEach(Consumer::stop);
        threads.forEach(Thread::interrupt);
        threads.clear();
    }

    public List<ResourceDTO> getResourceInfo() {
        List<ResourceDTO> resourceDTOs = new ArrayList<>();
        resources.forEach(resource -> {
            ResourceDTO dto = new ResourceDTO(
                    resource.getId(),
                    resource.getQuantity(),
                    resource.getMaxQuantity(),
                    resource.getMinQuantity()
            );
            resourceDTOs.add(dto);
        });
        return resourceDTOs;
    }
    public List<ProducerDTO> getProducerInfo() {
        List<ProducerDTO> producerDTOs = new ArrayList<>();
        producers.forEach(producer -> {
            ProducerDTO dto = new ProducerDTO(
                    producer.getId(),
                    producer.getResource().getId(),
                    producer.isRunning() ? "Running" : "Stopped",
                    producer.getTimesProduced(),
                    producer.getStartTime(),
                    producer.getStopTime()
            );
            producerDTOs.add(dto);
        });
        return producerDTOs;
    }

    public List<ConsumerDTO> getConsumerInfo() {
        List<ConsumerDTO> consumerDTOs = new ArrayList<>();
        consumers.forEach(consumer -> {
            ConsumerDTO dto = new ConsumerDTO(
                    consumer.getId(),
                    consumer.getResource().getId(),
                    consumer.isRunning() ? "Running" : "Stopped",
                    consumer.getTimesConsumed(),
                    consumer.getStartTime(),
                    consumer.getStopTime()
            );
            consumerDTOs.add(dto);
        });
        return consumerDTOs;
    }

    public int getTotalResourcesQuantity() {
        return resources.stream().mapToInt(Resources::getQuantity).sum();
    }

    public int getActiveThreads() {
        return (int) threads.stream().filter(Thread::isAlive).count();
    }
}
