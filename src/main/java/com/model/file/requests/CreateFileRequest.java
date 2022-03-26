package com.model.file.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateFileRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = true)
    public String path;

    @JsonProperty(required = true)
    public String type;

    @JsonProperty(required = false)
    public String content;

    @JsonCreator
    public CreateFileRequest(String id, String path, String type, String content) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.content = content;
    }

    public boolean hasContent() {
        return content != null;
    }
}
