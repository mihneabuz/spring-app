package com.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUsernameRequest {

    @JsonProperty(required = true)
    private String newUsername;

    public UpdateUsernameRequest() {}

    @JsonCreator
    public UpdateUsernameRequest(String newUsername) {
        this.newUsername = newUsername;
    }
}
