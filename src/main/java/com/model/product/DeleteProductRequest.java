package com.model.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteProductRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonCreator
    public DeleteProductRequest(String id) {
        this.id = id;
    }
}
