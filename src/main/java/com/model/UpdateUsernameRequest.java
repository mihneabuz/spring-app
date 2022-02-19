package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUsernameRequest {

    private String newUsername;

    public UpdateUsernameRequest() {}

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
