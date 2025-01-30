package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Producer implements Runnable{
    private final Resource resource;
    private volatile boolean running = true;
    private String state;
    private String startTime;
    private String stopTime;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    public void run() {
        startTime =
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        state = "Running";
        while (running) {
            produce();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        stopTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm" +
                ":ss"));
        state = "Stopped";
    }

    public void stop() {
        running = false;
    }

    private void produce() {
        synchronized (resource) {
            if (resource.getQuantity() < resource.getMaxQuantity()) {
                resource.increment();
            }
        }
    }
    public String getState() {
        return state;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStopTime() {
        return stopTime;
    }
}
