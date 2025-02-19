package org.example.model.dto;

import java.time.LocalTime;

public class ConsumerDTO {
    private int id;
    private int resourceId;
    private String status;
    private int timesConsumed;
    private LocalTime startTime;
    private LocalTime endTime;

    public ConsumerDTO(int id, int resourceId, String status, int timesConsumed, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.resourceId = resourceId;
        this.status = status;
        this.timesConsumed = timesConsumed;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getResourceId() { return resourceId; }
    public void setResourceId(int resourceId) { this.resourceId = resourceId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getTimesConsumed() { return timesConsumed; }
    public void setTimesConsumed(int timesConsumed) { this.timesConsumed = timesConsumed; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}