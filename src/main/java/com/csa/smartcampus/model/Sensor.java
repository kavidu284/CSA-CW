package com.csa.smartcampus.model;

import java.io.Serializable;

public class Sensor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String sensorId;
    private String sensorType;
    private String roomId;
    private boolean active;
    
    public Sensor() {
    }
    
    public Sensor(String sensorId, String sensorType, String roomId, boolean active) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.roomId = roomId;
        this.active = active;
    }
    
    // Getters and Setters
    public String getSensorId() {
        return sensorId;
    }
    
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
    
    public String getSensorType() {
        return sensorType;
    }
    
    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }
    
    public String getRoomId() {
        return roomId;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
