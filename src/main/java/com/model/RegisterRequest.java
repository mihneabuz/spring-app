package com.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {

    @JsonProperty(required = true)
    private String username;

    @JsonProperty(required = true)
    private String password;

    public RegisterRequest() {}

    @JsonCreator
    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
            "username:\'" + username + "\'," +
            "password:\'" + password + "\'" +
            "}";
    }
}
