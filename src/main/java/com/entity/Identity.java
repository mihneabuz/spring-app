package com.entity;

import lombok.Data;

@Data
public class Identity {

    public final String id;
    public final String username;
    public final int level;
    public final long expiresAt;
}
