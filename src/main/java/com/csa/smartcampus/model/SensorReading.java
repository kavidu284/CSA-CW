package com.csa.smartcampus.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SensorReading implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String readingId;
    private String sensorId;
    private double value;
    private LocalDateTime timestamp;
    
    public SensorReading() {
    }
    
    public SensorReading(String readingId, String sensorId, double value, LocalDateTime timestamp) {
        this.readingId = readingId;
        this.sensorId = sensorId;
        this.value = value;
        this.timestamp = timestamp;
    }
    
    // Getters and Setters
    public String getReadingId() {
        return readingId;
    }
    
    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }
    
    public String getSensorId() {
        return sensorId;
    }
    
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
