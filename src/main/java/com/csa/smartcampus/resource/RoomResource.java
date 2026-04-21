package com.csa.smartcampus.resource;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.csa.smartcampus.exception.ResourceAlreadyExistsException;
import com.csa.smartcampus.exception.ResourceNotFoundException;
import com.csa.smartcampus.exception.RoomNotEmptyException;
import com.csa.smartcampus.model.Room;
import com.csa.smartcampus.store.DataStore;

@Path("/rooms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

    private final Map<String, Room> rooms = DataStore.getInstance().getRooms();

    @GET
    public Response getAllRooms() {
        return Response.ok(new ArrayList<>(rooms.values())).build();
    }

    @POST
    public Response createRoom(Room room, @Context UriInfo uriInfo) {
        if (room == null) {
            throw new BadRequestException("Room data is required");
        }
        if (room.getId() == null || room.getId().trim().isEmpty()) {
            throw new BadRequestException("Room ID is required");
        }
        if (room.getName() == null || room.getName().trim().isEmpty()) {
            throw new BadRequestException("Room name is required");
        }
        if (room.getCapacity() <= 0) {
            throw new BadRequestException("Room capacity must be greater than 0");
        }
        if (rooms.containsKey(room.getId())) {
            throw new ResourceAlreadyExistsException("Room with this ID already exists");
        }

        if (room.getSensorIds() == null) {
            room.setSensorIds(new ArrayList<>());
        }

        rooms.put(room.getId(), room);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(room.getId());

        return Response.created(builder.build()).entity(room).build();
    }

    @GET
    @Path("/{roomId}")
    public Response getRoomById(@PathParam("roomId") String roomId) {
        Room room = rooms.get(roomId);
        if (room == null) {
            throw new ResourceNotFoundException("Room not found");
        }
        return Response.ok(room).build();
    }

    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        Room room = rooms.get(roomId);
        if (room == null) {
            throw new ResourceNotFoundException("Room not found");
        }
        if (room.getSensorIds() != null && !room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Cannot delete room because sensors are still assigned to it");
        }

        rooms.remove(roomId);
        return Response.ok("{\"message\":\"Room deleted successfully\"}").build();
    }
}