package com.model.agent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = true)
    public String pattern;

    @JsonCreator
    public SearchRequest(String id, String pattern) {
        this.id = id;
        this.pattern = pattern;
    }
}
