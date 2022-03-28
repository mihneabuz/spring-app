package com.model.file.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchPathRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = true)
    public String pattern;

    @JsonCreator
    public SearchPathRequest(String id, String pattern) {
        this.id = id;
        this.pattern = pattern;
    }
}
