package com.model.file.responses;

import com.model.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ShowContentResponse extends Response {

    private String content;

    public ShowContentResponse(String content) {
        super(true, "ok");
        this.content = content;
    }
}
