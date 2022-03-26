package com.model.agent;

import com.entity.MyFile;
import com.model.Response;

import java.util.List;

public class DirectoryResponse extends Response {

    List<MyFile> files;
    public DirectoryResponse(List<MyFile> files) {
        super(true, "ok");
        this.files = files;
    }
}
