package com.model.agent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = true)
    public String path;

    @JsonCreator
    public ContentRequest(String id, String path) {
        this.id = id;
        this.path = path;
    }
}
