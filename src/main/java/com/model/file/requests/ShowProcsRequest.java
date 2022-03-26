package com.model.file.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowProcsRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = false)
    public int count;

    @JsonProperty(required = false)
    public String sortBy;

    @JsonCreator
    public ShowProcsRequest(String id, int count, String sortBy) {
        this.id = id;
        this.count = count;
        this.sortBy = sortBy;
    }

    public boolean hasCount() {
        return count != 0;
    }

    public boolean hasSortBy() {
        return sortBy != null;
    }
}
