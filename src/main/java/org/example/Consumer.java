package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Consumer implements Runnable {
    private final Resource resource;
    private volatile boolean running = true;
    private String state;
    private String startTime;
    private String stopTime;
    private int timesConsumed = 0;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        startTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        state = "Running";
        while (running) {
            consume();
            try {
                Thread.sleep(2000); // 2 second delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        stopTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        state = "Stopped";
    }


    public void stop() {
        running = false;
    }

    private void consume() {
        synchronized (resource) {
            while (resource.getQuantity() <= resource.getMinQuantity()) {
                try {
                    resource.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            resource.decrement();
            timesConsumed++;
            resource.notifyAll();
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

    public int getTimesConsumed() {
        return timesConsumed;
    }

    public Resource getResource() {
        return resource;
    }
}
