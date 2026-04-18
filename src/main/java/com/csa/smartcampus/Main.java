package com.csa.smartcampus;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import com.csa.smartcampus.config.AppConfig;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/api/v1/";

    public static void main(String[] args) {
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                new AppConfig()
        );

        System.out.println("Server started at: " + BASE_URI);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            server.shutdownNow();
        }
    }
}