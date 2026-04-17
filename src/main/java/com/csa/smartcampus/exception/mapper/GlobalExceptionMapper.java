package com.csa.smartcampus.exception.mapper;

import com.csa.smartcampus.util.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {
    
    @Override
    public Response toResponse(Exception exception) {
        ApiError error = new ApiError("INTERNAL_ERROR", "An unexpected error occurred: " + exception.getMessage(), 500);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}
