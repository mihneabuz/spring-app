package com.model.file.responses;

import com.model.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SearchPathResponse extends Response {

    private String foundFiles;

    public SearchPathResponse(String foundFiles) {
        super(true, "ok");
        this.foundFiles = foundFiles;
    }
}
