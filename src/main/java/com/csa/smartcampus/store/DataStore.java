package com.csa.smartcampus.store;

import com.csa.smartcampus.model.Room;
import com.csa.smartcampus.model.Sensor;
import com.csa.smartcampus.model.SensorReading;

import java.util.*;

public class DataStore {
    private static DataStore instance;
    private Map<String, Room> rooms;
    private Map<String, Sensor> sensors;
    private Map<String, SensorReading> readings;
    
    private DataStore() {
        this.rooms = new HashMap<>();
        this.sensors = new HashMap<>();
        this.readings = new HashMap<>();
    }
    
    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }
    
    // Room operations
    public void addRoom(Room room) {
        rooms.put(room.getRoomId(), room);
    }
    
    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }
    
    public Collection<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }
    
    public void deleteRoom(String roomId) {
        rooms.remove(roomId);
    }
    
    // Sensor operations
    public void addSensor(Sensor sensor) {
        sensors.put(sensor.getSensorId(), sensor);
    }
    
    public Sensor getSensor(String sensorId) {
        return sensors.get(sensorId);
    }
    
    public Collection<Sensor> getAllSensors() {
        return new ArrayList<>(sensors.values());
    }
    
    public void deleteSensor(String sensorId) {
        sensors.remove(sensorId);
    }
    
    // SensorReading operations
    public void addReading(SensorReading reading) {
        readings.put(reading.getReadingId(), reading);
    }
    
    public SensorReading getReading(String readingId) {
        return readings.get(readingId);
    }
    
    public Collection<SensorReading> getAllReadings() {
        return new ArrayList<>(readings.values());
    }
}
