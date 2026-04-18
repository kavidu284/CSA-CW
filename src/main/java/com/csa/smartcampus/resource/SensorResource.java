package com.csa.smartcampus.resource;

import com.csa.smartcampus.model.Room;
import com.csa.smartcampus.model.Sensor;
import com.csa.smartcampus.store.DataStore;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            List<Sensor> filteredSensors = new ArrayList<>();

            for (Sensor sensor : sensorList) {
                if (sensor.getType() != null && sensor.getType().equalsIgnoreCase(type)) {
                    filteredSensors.add(sensor);
                }
            }

            return Response.ok(filteredSensors).build();
        }

        return Response.ok(sensorList).build();
    }

    @POST
    public Response createSensor(Sensor sensor, @Context UriInfo uriInfo) {
        if (sensor == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Sensor data is required")
                    .build();
        }

        if (sensor.getId() == null || sensor.getId().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Sensor ID is required")
                    .build();
        }

        if (sensor.getType() == null || sensor.getType().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Sensor type is required")
                    .build();
        }

        if (sensor.getStatus() == null || sensor.getStatus().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Sensor status is required")
                    .build();
        }

        if (sensor.getRoomId() == null || sensor.getRoomId().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room ID is required")
                    .build();
        }

        if (sensors.containsKey(sensor.getId())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Sensor with this ID already exists")
                    .build();
        }

        Room room = rooms.get(sensor.getRoomId());
        if (room == null) {
            return Response.status(422)
                    .entity("The specified roomId does not exist")
                    .build();
        }

        sensors.put(sensor.getId(), sensor);
        room.getSensorIds().add(sensor.getId());

        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(sensor.getId());

        return Response.created(builder.build())
                .entity(sensor)
                .build();
    }

    @GET
    @Path("/{sensorId}")
    public Response getSensorById(@PathParam("sensorId") String sensorId) {
        Sensor sensor = sensors.get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        return Response.ok(sensor).build();
    }

    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
}