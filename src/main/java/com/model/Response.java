package com.model;

public class Response {

    private boolean success;
    private String message;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
            "success:" + success + "," +
            "message:\'" + message + "\'" +
            "}";
    }

    public static Response good() {
        return new Response(true, "ok");
    }

    public static Response bad(String msg) {
        return new Response(false, msg);
    }
}
