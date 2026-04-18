package com.csa.smartcampus.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.csa.smartcampus.model.Sensor;
import com.csa.smartcampus.model.SensorReading;
import com.csa.smartcampus.store.DataStore;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private final String sensorId;
    private final Map<String, Sensor> sensors = DataStore.getInstance().getSensors();
    private final Map<String, List<SensorReading>> readings = DataStore.getInstance().getReadings();

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    public Response getAllReadings() {
        if (!sensors.containsKey(sensorId)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        List<SensorReading> sensorReadings = readings.get(sensorId);

        if (sensorReadings == null) {
            sensorReadings = new ArrayList<>();
        }

        return Response.ok(sensorReadings).build();
    }

    @POST
    public Response addReading(SensorReading reading, @Context UriInfo uriInfo) {
        Sensor sensor = sensors.get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        if (reading == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Reading data is required")
                    .build();
        }

        if (reading.getValue() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Reading value is required")
                    .build();
        }

        if (reading.getId() == null || reading.getId().trim().isEmpty()) {
            reading.setId(UUID.randomUUID().toString());
        }

        if (reading.getTimestamp() == 0) {
            reading.setTimestamp(System.currentTimeMillis());
        }

        List<SensorReading> sensorReadings = readings.get(sensorId);
        if (sensorReadings == null) {
            sensorReadings = new ArrayList<>();
            readings.put(sensorId, sensorReadings);
        }

        sensorReadings.add(reading);

        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED)
                .entity(reading)
                .build();
    }
}