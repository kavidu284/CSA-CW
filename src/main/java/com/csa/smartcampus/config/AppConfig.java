package com.csa.smartcampus.config;

import com.csa.smartcampus.resource.DiscoveryResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/v1")
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(DiscoveryResource.class);
        packages("org.glassfish.jersey.jackson");
    }
}