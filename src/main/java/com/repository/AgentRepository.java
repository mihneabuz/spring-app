package com.repository;

import com.auth.JwtOps;
import com.entity.Agent;
import com.entity.Identity;
import com.mongodb.client.MongoClient;
import config.MongoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
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

    public static Query queryIpPort(String ip, String port) {
        return new Query()
                .addCriteria(Criteria.where("ip").is(ip))
                .addCriteria(Criteria.where("port").is(port));
    }

    public Optional<Agent> findById(String id) {
        var list = mongoTemplate.find(queryId(id), Agent.class);

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    public Optional<Agent> findByIpPort(String ip, String port) {
        var list = mongoTemplate.find(
                queryIpPort(ip, port),
                Agent.class);

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }


    public void addAgent(final Agent agent) {
        mongoTemplate.insert(agent);
    }

    public void deleteAgent(String id) {
        mongoTemplate.findAndRemove(queryId(id), Agent.class);
    }


    public void updateStatus(String id, String newStatus) {
        mongoTemplate.findAndModify(queryId(id),
                                    new Update().set("status", newStatus),
                                    Agent.class);
    }

    public void updateHeartBeat(String id, long newHeartBeat) {
        mongoTemplate.findAndModify(queryId(id),
                new Update().set("heartbeat", newHeartBeat),
                Agent.class);
    }

    public void deleteInactiveAgents() {
        // Delete agents that have not responded to heartbeat in 30 sec
        var allAgents = getAllAgents();

        long currentTime = System.currentTimeMillis();
        for (Agent agent : allAgents) {
            if (currentTime - agent.getHeartbeat() >= 3000000) {
                mongoTemplate.findAndRemove(new Query(Criteria.where("heartbeat")
                                .is(Long.toString(agent.getHeartbeat()))),
                        Agent.class);
            }
        }
    }

    public List<Agent> getAllAgents() {
        return mongoTemplate.findAll(Agent.class);
    }

    public List<Agent> getPublicAgents() {
        return mongoTemplate.find(new Query()
                            .addCriteria(Criteria.where("owner").is("Public")),
                        Agent.class);
    }

    public List<Agent> getUserAgents(String auth) {
        Identity identity = JwtOps.decodeOrThrow(auth);

        return mongoTemplate.find(new Query()
                    .addCriteria(new Criteria().orOperator(
                            Criteria.where("owner").is("Public"),
                            Criteria.where("owner").is(identity.getId())
                    )),
                    Agent.class);
    }

    public long count() {
        return mongoTemplate.estimatedCount(Agent.class);
    }
}
