package com.csa.smartcampus.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.csa.smartcampus.exception.mapper.GlobalExceptionMapper;
import com.csa.smartcampus.exception.mapper.LinkedResourceNotFoundExceptionMapper;
import com.csa.smartcampus.exception.mapper.ResourceAlreadyExistsExceptionMapper;
import com.csa.smartcampus.exception.mapper.ResourceNotFoundExceptionMapper;
import com.csa.smartcampus.exception.mapper.RoomNotEmptyExceptionMapper;
import com.csa.smartcampus.exception.mapper.SensorUnavailableExceptionMapper;
import com.csa.smartcampus.filter.ApiLoggingFilter;
import com.csa.smartcampus.resource.DiscoveryResource;
import com.csa.smartcampus.resource.RoomResource;
import com.csa.smartcampus.resource.SensorResource;

public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(DiscoveryResource.class);
        register(RoomResource.class);
        register(SensorResource.class);

        register(RoomNotEmptyExceptionMapper.class);
        register(LinkedResourceNotFoundExceptionMapper.class);
        register(SensorUnavailableExceptionMapper.class);
        register(ResourceAlreadyExistsExceptionMapper.class);
        register(ResourceNotFoundExceptionMapper.class);
        register(GlobalExceptionMapper.class);

        register(ApiLoggingFilter.class);

        packages("org.glassfish.jersey.jackson");
    }
}