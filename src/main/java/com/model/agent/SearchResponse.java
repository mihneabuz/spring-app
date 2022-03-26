package com.model.agent;

import com.model.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SearchResponse extends Response {

    private String foundFiles;

    public SearchResponse(String foundFiles) {
        super(true, "ok");
        this.foundFiles = foundFiles;
    }
}
