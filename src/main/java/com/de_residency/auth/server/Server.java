package com.de_residency.auth.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.de_residency.auth.server.routes.Routes;
import com.sun.net.httpserver.HttpServer;

public class Server {

    public static void start() throws IOException {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080),
                0
        );

        Routes.register(server);
        server.start();

        System.out.println("Authentication Service started on port 8080");
    }
}
