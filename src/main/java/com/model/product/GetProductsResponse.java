package com.model.product;

import com.entity.Product;
import com.model.Response;
import lombok.Getter;

import java.util.List;

@Getter
public class GetProductsResponse extends Response {

    private final List<Product> products;

    public GetProductsResponse(List<Product> products) {
        super(true, "success");
        this.products = products;
    }
}
