package com.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "name:\'" + name + "\'," +
                "price:" + price + "," +
                "details:" + details +
                "}";
    }
}
