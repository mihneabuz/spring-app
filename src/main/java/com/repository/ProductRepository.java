package com.repository;

import com.entity.Product;
import com.mongodb.client.MongoClient;
import config.MongoConfig;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

    public static Query queryId(String id) {
        return new Query().addCriteria(Criteria.where("id").is(id));
    }

    public void addProduct(final Product product) {
        mongoTemplate.insert(product);
    }

    public List<Product> getProducts() {
        return mongoTemplate.findAll(Product.class);
    }

    public Optional<Product> findById(String id) {
        var list = mongoTemplate.find(queryId(id), Product.class);

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    public void updateName(String id, String newName) {
        mongoTemplate.findAndModify(queryId(id),
                                    new Update().set("name", newName),
                                    Product.class);
    }

    public void updatePrice(String id, int newPrice) {
        mongoTemplate.findAndModify(queryId(id),
                                    new Update().set("price", newPrice),
                                    Product.class);
    }

    public void updateDetails(String id, List<String> newDetails) {
        mongoTemplate.findAndModify(queryId(id),
                                    new Update().set("details", newDetails),
                                    Product.class);
    }

    public void deleteProduct(String id) {
        mongoTemplate.findAndRemove(queryId(id),
                                    Product.class);
    }

    public long count() {
        return mongoTemplate.estimatedCount(Product.class);
    }
}
