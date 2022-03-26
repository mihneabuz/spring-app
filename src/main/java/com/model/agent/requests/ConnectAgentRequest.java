package com.model.agent.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectAgentRequest {

    @JsonProperty(required = true)
    public String ip;

    @JsonProperty(required = true)
    public String port;

    @JsonProperty(required = true)
    public String root;

    @JsonProperty(required = false)
    public String token;

    @JsonCreator
    public ConnectAgentRequest(String ip, String port, String root, String token) {
        this.ip = ip;
        this.port = port;
        this.root = root;
        this.token = token;
    }

    public boolean hasToken() {
        return token != null;
    }
}
