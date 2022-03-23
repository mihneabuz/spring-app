package com.model;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class Response {

    private final boolean success;
    private final String message;

    protected Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static Response good() {
        return new Response(true, "ok");
    }

    public static Response bad(String msg) {
        return new Response(false, msg);
    }
}
