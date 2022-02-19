package com.entity;

public class Identity {
    
    public String id;
    public String username;
    public int level;
    public long expiresAt;

    public Identity(String id, String username, int level, long expiresAt) {
        this.id = id;
        this.username = username;
        this.level = level;
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
       return username;
    } 

    public int getLevel() {
        return level;
    }

    public long getExpiresAt() {
        return expiresAt;
    }
}
