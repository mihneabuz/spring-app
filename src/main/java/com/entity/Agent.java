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

    public String name;
    public String status;
    public String ip;
    public String port;
    public String root;
    public String owner;
    public long heartbeat;

    private Agent(String id, String status, String name, String ip,
                  String port, String root, String owner, long heartbeat) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.root = root;
        this.owner = owner;
        this.heartbeat = heartbeat;
    }

    public static Agent createNew(String status, String name, String ip, String port,
                                  String root, String owner, long heartBeat) {
        return new Agent(UUID.randomUUID().toString(), status, name, ip, port,
                root, owner, heartBeat);
    }
}
