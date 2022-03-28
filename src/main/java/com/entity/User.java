package com.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Document("users")
public class User {

    @Id
    public String id;

    public String email;
    public String username;
    public String password;
    public int level;

    private User(String id, String email, String username, String password, int level) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public static User createNew(String email, String username, String password) {
        return new User(UUID.randomUUID().toString(), email, username, password, 1);
    }
}
