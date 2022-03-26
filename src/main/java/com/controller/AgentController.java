package com.controller;

import com.auth.JwtOps;
import com.entity.Agent;
import com.entity.Identity;
import com.model.Response;
import com.model.agent.requests.ConnectAgentRequest;
import com.model.agent.requests.DisconnectAgentRequest;
import com.repository.AgentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin

@RestController
@RequestMapping("/agent")
public class AgentController {
    private static final Logger log = LoggerFactory.getLogger(AgentController.class);
    private static final AgentRepository agentRepo = AgentRepository.get();

    @PostMapping("/connect")
    public Response connect(@RequestBody ConnectAgentRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findByIpPort(body.getIp(), body.getPort());

        if (maybeAgent.isEmpty()) {
            Agent agent;
            if (body.hasToken()) {
                Identity identity = JwtOps.decodeOrThrow(body.getToken());

                agent = Agent.createNew("connected",
                                        body.getIp(), body.getPort(), body.getRoot(),
                                        identity.getId(), System.currentTimeMillis());
            } else {
                agent = Agent.createNew("connected",
                                        body.getIp(), body.getPort(), body.getRoot(),
                                        "Public", System.currentTimeMillis());
            }
            agentRepo.addAgent(agent);
        } else {
            agentRepo.updateStatus(maybeAgent.get().getId(), "connected");
        }

        return Response.good();
    }

    @PostMapping("/disconnect")
    public Response disconnect(@RequestBody DisconnectAgentRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findByIpPort(body.getIp(), body.getPort());

        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

//        agentRepo.updateStatus(maybeAgent.get().getId(), "disconnected");
        agentRepo.deleteAgent(maybeAgent.get().getId());
        return Response.good();
    }

}
