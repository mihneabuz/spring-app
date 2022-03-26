package com.model.file.responses;

import com.entity.Agent;
import com.model.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class ShowAgentsResponse extends Response {

    private List<Agent> agents;

    public ShowAgentsResponse(List<Agent> agents) {
        super(true, "ok");
        this.agents = agents;
    }
}
