package com.csa.smartcampus.resource;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csa.smartcampus.model.SensorReading;
import com.csa.smartcampus.store.DataStore;

@Path("/readings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
    
    private final DataStore dataStore = DataStore.getInstance();
    
    @GET
    public Collection<SensorReading> getAllReadings() {
        return dataStore.getAllReadings();
    }
    
    @GET
    @Path("/{readingId}")
    public Response getReadingById(@PathParam("readingId") String readingId) {
        SensorReading reading = dataStore.getReading(readingId);
        if (reading == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(reading).build();
    }
    
    @POST
    public Response createReading(SensorReading reading) {
        dataStore.addReading(reading);
        return Response.status(Response.Status.CREATED).entity(reading).build();
    }
}
