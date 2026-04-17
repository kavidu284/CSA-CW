package com.csa.smartcampus.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/discovery")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {
    
    @GET
    public Map<String, String> getDiscoveryInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("app", "Smart Campus API");
        info.put("version", "1.0.0");
        info.put("endpoints", "/api/rooms, /api/sensors, /api/readings");
        return info;
    }
}
