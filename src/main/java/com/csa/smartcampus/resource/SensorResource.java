package com.csa.smartcampus.resource;

import com.csa.smartcampus.model.Sensor;
import com.csa.smartcampus.store.DataStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {
    
    private final DataStore dataStore = DataStore.getInstance();
    
    @GET
    public Collection<Sensor> getAllSensors() {
        return dataStore.getAllSensors();
    }
    
    @GET
    @Path("/{sensorId}")
    public Response getSensorById(@PathParam("sensorId") String sensorId) {
        Sensor sensor = dataStore.getSensor(sensorId);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(sensor).build();
    }
    
    @POST
    public Response createSensor(Sensor sensor) {
        dataStore.addSensor(sensor);
        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }
    
    @PUT
    @Path("/{sensorId}")
    public Response updateSensor(@PathParam("sensorId") String sensorId, Sensor sensor) {
        sensor.setSensorId(sensorId);
        dataStore.addSensor(sensor);
        return Response.ok(sensor).build();
    }
    
    @DELETE
    @Path("/{sensorId}")
    public Response deleteSensor(@PathParam("sensorId") String sensorId) {
        dataStore.deleteSensor(sensorId);
        return Response.noContent().build();
    }
}
