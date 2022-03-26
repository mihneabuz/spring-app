package com.model.agent;

import com.model.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DirectoryResponse extends Response {

    private String body;

    public DirectoryResponse(String body) {
        super(true, "ok");
        this.body = body;
    }
}
