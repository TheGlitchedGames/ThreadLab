package org.example.model;

import java.time.LocalTime;

public class Consumer implements Runnable{
    private int id;
    private Resources resource;
    private volatile boolean state;
    private LocalTime startTime;
    private LocalTime stopTime;
    private int timesConsumed;

    public Consumer(int id, Resources resource) {
        this.id = id;
        this.resource = resource;
        this.state = false;
        this.timesConsumed = 0;
    }

    @Override
    public void run() {
        state = true;
        startTime = LocalTime.now();

        while (state) {

            if (consume()) {
                timesConsumed++;
            }
        }

        stopTime = LocalTime.now();
    }

    private boolean consume() {
        return resource.removeResource();
    }

    public void stop() {
        state = false;
    }

    public Resources getResource() { return resource; }
    public boolean isRunning() { return state; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getStopTime() { return stopTime; }
    public int getTimesConsumed() { return timesConsumed; }
    public int getId() { return id; }
}
