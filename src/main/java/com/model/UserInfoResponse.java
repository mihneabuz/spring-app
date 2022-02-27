package com.model;

public class UserInfoResponse extends Response {
        
    private String username;
    private long level;

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
