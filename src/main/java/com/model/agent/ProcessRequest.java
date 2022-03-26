package com.model.agent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = true)
    public int count;

    @JsonProperty(required = false)
    public String sortBy;

    public boolean hasSortBy() {
        return (sortBy != null);
    }

    @JsonCreator
    public ProcessRequest(String id, int count, String sortBy) {
        this.id = id;
        this.count = count;
        this.sortBy = sortBy;
    }
}
