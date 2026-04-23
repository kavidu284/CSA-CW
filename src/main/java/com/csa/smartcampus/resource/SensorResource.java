package com.csa.smartcampus.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.csa.smartcampus.exception.LinkedResourceNotFoundException;
import com.csa.smartcampus.exception.ResourceAlreadyExistsException;
import com.csa.smartcampus.exception.ResourceNotFoundException;
import com.csa.smartcampus.model.Room;
import com.csa.smartcampus.model.Sensor;
import com.csa.smartcampus.store.DataStore;
import javax.ws.rs.DELETE;

@Path("/sensors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SensorResource {

    private final Map<String, Sensor> sensors = DataStore.getInstance().getSensors();
    private final Map<String, Room> rooms = DataStore.getInstance().getRooms();

    @GET
    public Response getAllSensors(@QueryParam("type") String type) {
        List<Sensor> sensorList = new ArrayList<>(sensors.values());

        if (type != null && !type.trim().isEmpty()) {
            List<Sensor> filtered = new ArrayList<>();
            for (Sensor sensor : sensorList) {
                if (sensor.getType() != null && sensor.getType().equalsIgnoreCase(type)) {
                    filtered.add(sensor);
                }
            }
            return Response.ok(filtered).build();
        }

        return Response.ok(sensorList).build();
    }

    @POST
    public Response createSensor(Sensor sensor, @Context UriInfo uriInfo) {
        if (sensor == null) {
            throw new BadRequestException("Sensor data is required");
        }
        if (sensor.getId() == null || sensor.getId().trim().isEmpty()) {
            throw new BadRequestException("Sensor ID is required");
        }
        if (sensor.getType() == null || sensor.getType().trim().isEmpty()) {
            throw new BadRequestException("Sensor type is required");
        }
        if (sensor.getStatus() == null || sensor.getStatus().trim().isEmpty()) {
            throw new BadRequestException("Sensor status is required");
        }
        if (sensor.getRoomId() == null || sensor.getRoomId().trim().isEmpty()) {
            throw new BadRequestException("Room ID is required");
        }
        if (sensors.containsKey(sensor.getId())) {
            throw new ResourceAlreadyExistsException("Sensor with this ID already exists");
        }

        Room room = rooms.get(sensor.getRoomId());
        if (room == null) {
            throw new LinkedResourceNotFoundException("The specified roomId does not exist");
        }

        sensors.put(sensor.getId(), sensor);
        room.getSensorIds().add(sensor.getId());

        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(sensor.getId());
        return Response.created(builder.build()).entity(sensor).build();
    }

    @GET
    @Path("/{sensorId}")
    public Response getSensorById(@PathParam("sensorId") String sensorId) {
        Sensor sensor = sensors.get(sensorId);
        if (sensor == null) {
            throw new ResourceNotFoundException("Sensor not found");
        }
        return Response.ok(sensor).build();
    }

    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
    @DELETE
    @Path("/{sensorId}")
    public Response deleteSensor(@PathParam("sensorId") String sensorId) {
        Sensor sensor = sensors.get(sensorId);

        if (sensor == null) {
            throw new ResourceNotFoundException("Sensor not found");
        }

        Room room = rooms.get(sensor.getRoomId());
        if (room != null && room.getSensorIds() != null) {
            room.getSensorIds().remove(sensorId);
        }

        sensors.remove(sensorId);
        DataStore.getInstance().getReadings().remove(sensorId);

        return Response.noContent().build();
    }
}