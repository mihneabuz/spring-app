package com.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteUserRequest {

    @JsonProperty(required = true)
    private String password;

    public DeleteUserRequest() {}

    @JsonCreator
    public DeleteUserRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DeleteUserRequest{" +
            "password:\'" + password + "\'" +
            "}";
    }
}
