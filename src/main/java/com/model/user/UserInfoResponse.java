package com.model.user;

import com.model.Response;
import lombok.Getter;

@Getter
public class UserInfoResponse extends Response {
        
    private final String username;
    private final long level;

    public UserInfoResponse(String username, long level) {
        super(true, "ok");
        this.username = username;
        this.level = level;
    }
}
