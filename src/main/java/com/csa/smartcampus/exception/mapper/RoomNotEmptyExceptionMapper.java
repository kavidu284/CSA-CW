package com.csa.smartcampus.exception.mapper;

import com.csa.smartcampus.exception.RoomNotEmptyException;
import com.csa.smartcampus.util.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {
    
    @Override
    public Response toResponse(RoomNotEmptyException exception) {
        ApiError error = new ApiError("ROOM_NOT_EMPTY", exception.getMessage(), 409);
        return Response
                .status(Response.Status.CONFLICT)
                .entity(error)
                .build();
    }
}
