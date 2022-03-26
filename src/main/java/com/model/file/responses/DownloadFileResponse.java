package com.model.file.responses;

import com.model.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DownloadFileResponse extends Response {

    private String base64File;

    public DownloadFileResponse(String base64File) {
        super(true, "ok");
        this.base64File = base64File;
    }
}
