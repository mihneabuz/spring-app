package com.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter @Setter
@Document("agents")
public class Agent {

    @Id
    public String id;

    public String status;
    public String ip;
    public String port;
    public String root;

    private Agent(String id, String status, String ip, String port, String root) {
        this.id = id;
        this.status = status;
        this.ip = ip;
        this.port = port;
        this.root = root;
    }

    public static Agent createNew(String status, String ip, String port, String root) {
        return new Agent(UUID.randomUUID().toString(), status, ip, port, root);
    }
}
