package com.model.user;

import com.model.Response;

public class LoginResponse extends Response {

    private final String jwtToken;

    public LoginResponse(String jwtToken) {
        super(true, "ok");
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
