package com.csa.smartcampus.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.csa.smartcampus.resource.DiscoveryResource;
import com.csa.smartcampus.resource.RoomResource;
import com.csa.smartcampus.resource.SensorResource;

@ApplicationPath("/api/v1")
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(DiscoveryResource.class);
        register(RoomResource.class);
        register(SensorResource.class);
        packages("org.glassfish.jersey.jackson");
    }
}