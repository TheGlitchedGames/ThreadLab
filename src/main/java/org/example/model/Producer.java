package org.example.model;

import java.time.LocalTime;

public class Producer implements Runnable{
    private int id;
    private Resources resources;
    private volatile boolean state;
    private LocalTime startTime;
    private LocalTime stopTime;
    private int timesProduced;

    public Producer(int id, Resources resource) {
        this.id = id;
        this.resources = resource;
        this.state = false;
        this.timesProduced = 0;
    }

    @Override
    public void run() {
        state = true;
        startTime = LocalTime.now();

        while (state) {
            try {
                if (produce()) {
                    timesProduced++;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        stopTime = LocalTime.now();
    }

    private boolean produce() {
        return resources.addResource();
    }

    public void stop() {
        state = false;
    }

    public Resources getResource() { return resources; }
    public boolean isRunning() { return state; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getStopTime() { return stopTime; }
    public int getTimesProduced() { return timesProduced; }
    public int getId() { return id; }
}
