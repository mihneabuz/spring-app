package com.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document("product")
public class Product {

    @Id
    public String id;

    public String name;
    public int price;
    public List<String> details;

    public Product(String id, String name, int price, List<String> details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public static Product createNew(final String name, final int price, final List<String> details) {
        return new Product(UUID.randomUUID().toString(), name, price, details);
    }
}
