package com.model.agent.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisconnectAgentRequest {

    @JsonProperty(required = true)
    public String ip;

    @JsonProperty(required = true)
    public String port;

    @JsonCreator
    public DisconnectAgentRequest(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }
}
