package com.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUsernameRequest {

    @JsonProperty(required = true)
    private String newUsername;

    public UpdateUsernameRequest() {}

    @JsonCreator
    public UpdateUsernameRequest(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    @Override
    public String toString() {
        return "UpdateUsernameRequest{" +
            "newUsername:\'" + newUsername + "\'" +
            "}";
    }
}
