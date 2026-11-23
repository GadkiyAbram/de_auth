
package com.de_residency.auth.server.routes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Routes {

    public static void register(HttpServer server) {

        // GET /health
        server.createContext("/health", exchange -> {

            String response = """
                    {
                        "status": "UP",
                        "service": "auth"
                    }
                    """;

            sendJson(exchange, 200, response);
        });

        // GET /api/v1/auth/status
        server.createContext("/api/v1/auth/status", exchange -> {

            String response = """
                    {
                        "authentication": "available"
                    }
                    """;

            sendJson(exchange, 200, response);
        });

        // POST /api/v1/auth/register
        server.createContext("/api/v1/auth/register", exchange -> {
            // TODO: Implement user registration
        });

        // POST /api/v1/auth/login
        server.createContext("/api/v1/auth/login", exchange -> {
            // TODO: Implement user authentication
        });

        // PUT /api/v1/users/me
        server.createContext("/api/v1/users/me", exchange -> {
            // TODO: Implement user update
        });

        // GET /api/v1/users
        server.createContext("/api/v1/users", exchange -> {
            // TODO: Implement retrieving users
        });
    }

    private static void sendJson(
            HttpExchange exchange,
            int statusCode,
            String response
    ) throws IOException {

        byte[] responseBytes =
                response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
                "Content-Type",
                "application/json; charset=UTF-8"
        );

        exchange.sendResponseHeaders(
                statusCode,
                responseBytes.length
        );

        try (var responseBody = exchange.getResponseBody()) {
            responseBody.write(responseBytes);
        }
    }
}
