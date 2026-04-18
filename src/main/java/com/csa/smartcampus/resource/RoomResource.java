package com.csa.smartcampus.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.csa.smartcampus.model.Room;
import com.csa.smartcampus.store.DataStore;

@Path("/rooms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

    private final Map<String, Room> rooms = DataStore.getInstance().getRooms();

    @GET
    public Response getAllRooms() {
        List<Room> roomList = new ArrayList<>(rooms.values());
        return Response.ok(roomList).build();
    }

    @POST
    public Response createRoom(Room room, @Context UriInfo uriInfo) {
        if (room == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room data is required")
                    .build();
        }

        if (room.getId() == null || room.getId().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room ID is required")
                    .build();
        }

        if (room.getName() == null || room.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room name is required")
                    .build();
        }

        if (room.getCapacity() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room capacity must be greater than 0")
                    .build();
        }

        if (rooms.containsKey(room.getId())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Room with this ID already exists")
                    .build();
        }

        if (room.getSensorIds() == null) {
            room.setSensorIds(new ArrayList<>());
        }

        rooms.put(room.getId(), room);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(room.getId());

        return Response.created(builder.build())
                .entity(room)
                .build();
    }

    @GET
    @Path("/{roomId}")
    public Response getRoomById(@PathParam("roomId") String roomId) {
        Room room = rooms.get(roomId);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        return Response.ok(room).build();
    }

    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        Room room = rooms.get(roomId);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        if (room.getSensorIds() != null && !room.getSensorIds().isEmpty()) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Cannot delete room because sensors are still assigned to it")
                    .build();
        }

        rooms.remove(roomId);

        return Response.ok("Room deleted successfully").build();
    }
}