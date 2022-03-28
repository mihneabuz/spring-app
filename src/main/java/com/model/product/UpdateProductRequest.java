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
public class UpdateProductRequest {

    @JsonProperty(required = true)
    public String id;

    @JsonProperty(required = false)
    public String name;

    @JsonProperty(required = false)
    public int price;

    @JsonProperty(required = false)
    public List<String> details;

    @JsonCreator
    public UpdateProductRequest(String id, String name, int price, List<String> details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public boolean hasName() {
        return name != null;
    }

    public boolean hasPrice() {
        return price != 0;
    }

    public boolean hasDetails() {
        return details != null;
    }
}
