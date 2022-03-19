package com.repository;

import com.entity.Product;
import com.mongodb.client.MongoClient;
import config.MongoConfig;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    public static MongoClient mongoCLient;
    public static MongoTemplate mongoTemplate;

    private static ProductRepository instance;

    public static ProductRepository get() {
        if (instance == null) {
            instance = new ProductRepository();
        }

        return instance;
    }

    private ProductRepository() {
        MongoConfig config = new MongoConfig();
        mongoCLient = config.mongoClient();
        mongoTemplate = new MongoTemplate(mongoCLient, config.getDatabaseName());
    }

    public void addProduct(final Product product) {
        mongoTemplate.insert(product);
    }

    public List<Product> getProducts() {
        return mongoTemplate.findAll(Product.class);
    }
}
