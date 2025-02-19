package org.example.model.dto;

import java.time.LocalTime;

public class ProducerDTO {
    private int id;
    private int resourceId;
    private String status;
    private int timesProduced;
    private LocalTime startTime;
    private LocalTime endTime;

    public ProducerDTO(int id, int resourceId, String status, int timesProduced, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.resourceId = resourceId;
        this.status = status;
        this.timesProduced = timesProduced;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getResourceId() { return resourceId; }
    public void setResourceId(int resourceId) { this.resourceId = resourceId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getTimesProduced() { return timesProduced; }
    public void setTimesProduced(int timesProduced) { this.timesProduced = timesProduced; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}
