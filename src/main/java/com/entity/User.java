package com.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

    @Id
    public String id;

    public String email;
    public String username;
    public String password;
    public int level;

    public User(String id, String email, String username, String password, int level) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    } 

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static User createNew(String email, String username, String password) {
        return new User(UUID.randomUUID().toString(), email, username, password, 1);
    }
}
