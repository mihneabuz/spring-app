package com.model.agent;

import com.entity.Agent;
import com.model.Response;

import java.util.ArrayList;
import java.util.List;

public class AgentsResponse extends Response {

    private List<Agent> agents;

    public AgentsResponse(List<Agent> agents) {
        super(true, "ok");
        this.agents = agents;
    }
}
