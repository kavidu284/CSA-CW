package com.csa.smartcampus.resource;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csa.smartcampus.model.Room;
import com.csa.smartcampus.store.DataStore;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    
    private final DataStore dataStore = DataStore.getInstance();
    
    @GET
    public Collection<Room> getAllRooms() {
        return dataStore.getAllRooms();
    }
    
    @GET
    @Path("/{roomId}")
    public Response getRoomById(@PathParam("roomId") String roomId) {
        Room room = dataStore.getRoom(roomId);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(room).build();
    }
    
    @POST
    public Response createRoom(Room room) {
        dataStore.addRoom(room);
        return Response.status(Response.Status.CREATED).entity(room).build();
    }
    
    @PUT
    @Path("/{roomId}")
    public Response updateRoom(@PathParam("roomId") String roomId, Room room) {
        room.setRoomId(roomId);
        dataStore.addRoom(room);
        return Response.ok(room).build();
    }
    
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        dataStore.deleteRoom(roomId);
        return Response.noContent().build();
    }
}
