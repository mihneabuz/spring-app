package com.repository;

import com.entity.Agent;
import com.mongodb.client.MongoClient;
import config.MongoConfig;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

public class AgentRepository {
    public static MongoClient mongoCLient;
    public static MongoTemplate mongoTemplate;

    private static AgentRepository instance;

    public static AgentRepository get() {
        if (instance == null) {
            instance = new AgentRepository();
        }

        return instance;
    }

    private AgentRepository() {
        MongoConfig config = new MongoConfig();
        mongoCLient = config.mongoClient();
        mongoTemplate = new MongoTemplate(mongoCLient, config.getDatabaseName());
    }

    public static Query queryId(String id) {
        return new Query().addCriteria(Criteria.where("id").is(id));
    }

    public void addAgent(final Agent agent) {
        mongoTemplate.insert(agent);
    }

    public void deleteAgent(String id) {
        mongoTemplate.findAndRemove(queryId(id),
                Agent.class);
    }

    public Optional<Agent> findById(String id) {
        var list = mongoTemplate.find(queryId(id), Agent.class);

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    public long count() {
        return mongoTemplate.estimatedCount(Agent.class);
    }
}
