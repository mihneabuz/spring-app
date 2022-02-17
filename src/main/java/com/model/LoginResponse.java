package com.model;

public class LoginResponse extends Response {

    private String id;

    public LoginResponse(String id) {
        super(true, "ok");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
