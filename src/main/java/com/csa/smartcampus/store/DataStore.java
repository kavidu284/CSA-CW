package com.csa.smartcampus.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csa.smartcampus.model.Room;
import com.csa.smartcampus.model.Sensor;
import com.csa.smartcampus.model.SensorReading;

public class DataStore {

    private static final DataStore instance = new DataStore();

    private final Map<String, Room> rooms = new HashMap<>();
    private final Map<String, Sensor> sensors = new HashMap<>();
    private final Map<String, List<SensorReading>> readings = new HashMap<>();

    private DataStore() {
    }

    public static DataStore getInstance() {
        return instance;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public Map<String, Sensor> getSensors() {
        return sensors;
    }

    public Map<String, List<SensorReading>> getReadings() {
        return readings;
    }
}