package com.model;

import com.entity.Product;

import java.util.List;

public class GetProductsResponse extends Response {

    private final List<Product> products;

    public GetProductsResponse(List<Product> products) {
        super(true, "success");
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
