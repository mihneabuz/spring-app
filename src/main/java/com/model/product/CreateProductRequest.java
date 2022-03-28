package com.model.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProductRequest {

    @JsonProperty(required = true)
    public String name;

    @JsonProperty(required = true)
    public int price;

    @JsonProperty(required = true)
    public List<String> details;

    @JsonCreator
    public CreateProductRequest(String name, int price, List<String> details) {
        this.name = name;
        this.price = price;
        this.details = details;
    }
}
