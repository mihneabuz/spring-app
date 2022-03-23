package com.model.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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
        return "UpdateProductRequest{" +
                "id:\'" + id + "\'," +
                "name:\'" + name + "\'," +
                "price:" + price + "," +
                "details:" + details +
                "}";
    }
}
