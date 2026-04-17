package com.csa.smartcampus.exception.mapper;

import com.csa.smartcampus.exception.ResourceAlreadyExistsException;
import com.csa.smartcampus.util.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceAlreadyExistsExceptionMapper implements ExceptionMapper<ResourceAlreadyExistsException> {
    
    @Override
    public Response toResponse(ResourceAlreadyExistsException exception) {
        ApiError error = new ApiError("RESOURCE_ALREADY_EXISTS", exception.getMessage(), 409);
        return Response
                .status(Response.Status.CONFLICT)
                .entity(error)
                .build();
    }
}
