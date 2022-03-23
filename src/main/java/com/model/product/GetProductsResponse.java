package com.model.product;

import com.entity.Product;
import com.model.Response;

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
