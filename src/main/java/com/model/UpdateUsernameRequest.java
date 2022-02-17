package com.model;

public class UpdateUsernameRequest {

    private String id;
    private String newUsername;

    public UpdateUsernameRequest(String id, String newUsername) {
        this.id = id;
        this.newUsername = newUsername;
    }

    public String getId() {
        return id;
    }

    public String getNewUsername() {
        return newUsername;
    }

    @Override
    public String toString() {
        return "UpdateUsernameRequest{" +
            "id:\'" + id + "\'," +
            "newUsername:\'" + newUsername + "\'" +
            "}";
    }
}
