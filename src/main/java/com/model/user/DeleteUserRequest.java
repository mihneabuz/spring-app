package com.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteUserRequest {

    @JsonProperty(required = true)
    private String password;

    public DeleteUserRequest() {}

    @JsonCreator
    public DeleteUserRequest(String password) {
        this.password = password;
    }
}
