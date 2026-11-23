package com.de_residency.auth;

import java.io.IOException;

import com.de_residency.auth.server.Server;

public class Main {
    public static void main(String[] args) {
         try {
            Server.start();
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
            System.exit(1);
        }
    }
}
