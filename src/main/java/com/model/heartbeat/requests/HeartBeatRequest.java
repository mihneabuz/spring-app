package com.model.heartbeat.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeartBeatRequest {

    @JsonProperty(required = true)
    public String ip;

    @JsonProperty(required = true)
    public String port;

    @JsonCreator
    public HeartBeatRequest(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }
}
