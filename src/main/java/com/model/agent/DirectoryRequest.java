package com.model.agent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectoryRequest {

    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String path;

    public DirectoryRequest() {}

    @JsonCreator
    public DirectoryRequest(String id, String path) {
        this.id = id;
        this.path = path;
    }

}

