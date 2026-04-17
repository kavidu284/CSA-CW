package com.csa.smartcampus.exception.mapper;

import com.csa.smartcampus.exception.LinkedResourceNotFoundException;
import com.csa.smartcampus.util.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    
    @Override
    public Response toResponse(LinkedResourceNotFoundException exception) {
        ApiError error = new ApiError("LINKED_RESOURCE_NOT_FOUND", exception.getMessage(), 404);
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
