package com.repository;

import com.entity.User;
import config.MongoConfig;

import java.util.Optional;
import com.mongodb.client.MongoClient;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class UserRepository  {

    public static MongoClient mongoCLient;
    public static MongoTemplate mongoTemplate;

    private static UserRepository instance;
    
    public static UserRepository get() {
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    private UserRepository() {
        MongoConfig config = new MongoConfig();
        mongoCLient = config.mongoClient();
        mongoTemplate = new MongoTemplate(mongoCLient, config.getDatabaseName());
    }

    private static Query queryID(String id) {
        return new Query().addCriteria(Criteria.where("id").is(id));
    }

    public void addUser(String email, String username, String password) {
        User x = User.createNew(email, username, password);
        mongoTemplate.insert(x);
    }

    public Optional<User> findByID(String id) {
        var list = mongoTemplate.find(queryID(id), User.class);

        if (list.isEmpty())
            return Optional.empty();

        return Optional.of(list.get(0));
    }

    public Optional<User> findByEmail(String email) {
        var list = mongoTemplate.find(new Query().addCriteria(Criteria.where("email").is(email)), User.class);

        if (list.isEmpty())
            return Optional.empty();

        return Optional.of(list.get(0));
    } 

    public Optional<User> findByUsername(String username) {
        var list = mongoTemplate.find(new Query().addCriteria(Criteria.where("username").is(username)), User.class);

        if (list.isEmpty())
            return Optional.empty();

        return Optional.of(list.get(0));
    } 

    public void updateEmail(String id, String newEmail) {
        mongoTemplate.findAndModify(queryID(id), 
                                    new Update().set("email", newEmail),
                                    User.class);
    }

    public void updateUsername(String id, String newUsername) {
        mongoTemplate.findAndModify(queryID(id), 
                                    new Update().set("username", newUsername),
                                    User.class);
    }

    public void updatePassword(String id, String newPassword) {
        mongoTemplate.findAndModify(queryID(id), 
                                    new Update().set("password", newPassword),
                                    User.class);
    }

    public void deleteUser(String id) {
        mongoTemplate.findAndRemove(queryID(id), User.class);
    }

    public long count() {
        return mongoTemplate.estimatedCount(User.class);
    }
}
