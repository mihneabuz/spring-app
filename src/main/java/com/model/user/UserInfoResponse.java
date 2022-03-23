package com.model.user;

import com.model.Response;

public class UserInfoResponse extends Response {
        
    private final String username;
    private final long level;

    public UserInfoResponse(String username, long level) {
        super(true, "ok");
        this.username = username;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public long getLevel() {
        return level;
    }
}
