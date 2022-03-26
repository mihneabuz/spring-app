package com.model.agent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileCreateRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = true)
    public String path;

    @JsonProperty(required = true)
    public String type;

    @JsonProperty(required = false)
    public String content;

    @JsonCreator
    public FileCreateRequest(String id, String path, String type, String content) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.content = content;
    }
}
