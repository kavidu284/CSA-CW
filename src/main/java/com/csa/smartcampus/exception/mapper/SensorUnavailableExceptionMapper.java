package com.csa.smartcampus.exception.mapper;

import com.csa.smartcampus.exception.SensorUnavailableException;
import com.csa.smartcampus.util.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {
    
    @Override
    public Response toResponse(SensorUnavailableException exception) {
        ApiError error = new ApiError("SENSOR_UNAVAILABLE", exception.getMessage(), 503);
        return Response
                .status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(error)
                .build();
    }
}
