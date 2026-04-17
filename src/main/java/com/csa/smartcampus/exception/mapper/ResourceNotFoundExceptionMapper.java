package com.csa.smartcampus.exception.mapper;

import com.csa.smartcampus.exception.ResourceNotFoundException;
import com.csa.smartcampus.util.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    
    @Override
    public Response toResponse(ResourceNotFoundException exception) {
        ApiError error = new ApiError("RESOURCE_NOT_FOUND", exception.getMessage(), 404);
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
