package com.model.file.responses;

import com.model.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ShowProcsResponse extends Response {

    private String runningProcs;

    public ShowProcsResponse(String runningProcs) {
        super(true, "ok");
        this.runningProcs = runningProcs;
    }
}
