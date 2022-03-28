package com.model.file.responses;

import com.entity.Agent;
import com.model.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class ShowAgentsResponse extends Response {

    private List<AgentInfo> agents;

    @Getter @Setter @AllArgsConstructor @ToString
    public class AgentInfo {
        private String id;
        private String name;
    }

    public ShowAgentsResponse(List<Agent> agents) {
        super(true, "ok");
        this.agents = new ArrayList<>();
        for (Agent agent : agents) {
            this.agents.add(new AgentInfo(agent.getId(), agent.getName()));
        }
    }
}
