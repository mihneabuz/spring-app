package com.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {

    @JsonProperty(required = true)
    private String email;

    @JsonProperty(required = true)
    private String username;

    @JsonProperty(required = true)
    private String password;

    public RegisterRequest() {}

    @JsonCreator
    public RegisterRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
            "email:\'" + email + "\'," +
            "username:\'" + username + "\'," +
            "password:\'" + password + "\'" +
            "}";
    }
}
