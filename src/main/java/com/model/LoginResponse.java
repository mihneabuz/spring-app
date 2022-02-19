package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class LoginResponse extends Response {

    private String jwtToken;

    public LoginResponse(String jwtToken) {
        super(true, "ok");
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
