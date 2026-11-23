package com.de_residency.auth.model;

public class User {
    private final long id;
    private String email;
    private boolean isActive;

    public User(long id, String email, boolean isActive) {
        this.id = id;
        this.email = email;
        this.isActive = isActive;
    }

    public long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
