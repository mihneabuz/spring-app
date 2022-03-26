package com.model.file.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadFileRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = true)
    public String path;

    @JsonProperty(required = true)
    public String base64file;

    @JsonCreator
    public UploadFileRequest(String id, String path, String base64file) {
        this.id = id;
        this.path = path;
        this.base64file = base64file;
    }

}
