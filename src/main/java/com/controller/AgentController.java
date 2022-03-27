package com.controller;

import com.EncryptingUtils;
import com.auth.JwtOps;
import com.entity.Agent;
import com.entity.Identity;
import com.model.Response;
import com.model.agent.requests.ConnectAgentRequest;
import com.model.agent.requests.DisconnectAgentRequest;
import com.model.heartbeat.requests.HeartBeatRequest;
import com.repository.AgentRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@CrossOrigin

@RestController
@RequestMapping("/agent")
public class AgentController {
    private static final Logger log = LoggerFactory.getLogger(AgentController.class);
    private static final AgentRepository agentRepo = AgentRepository.get();

    private String sendPublicKey(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/publicKey";
        String jsonRequestBody = new JSONObject()
                .put("publicKey", EncryptingUtils.getPublicKey())
                .toString();

        var client = HttpClient.newHttpClient();

        try {
            URI uri = new URI(url);

            var request = HttpRequest.newBuilder(uri).
                    POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                    .header("Content-type", "application/json")
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    @PostMapping("/connect")
    public Response connect(@RequestBody ConnectAgentRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findByIpPort(body.getIp(), body.getPort());

        if (maybeAgent.isEmpty()) {
            Agent agent;
            if (body.hasToken()) {
                Identity identity = JwtOps.decodeOrThrow(body.getToken());

                agent = Agent.createNew("connected",
                                        body.getName(), body.getIp(), body.getPort(), body.getRoot(),
                                        identity.getId(), System.currentTimeMillis());
            } else {
                agent = Agent.createNew("connected",
                                        body.getName(), body.getIp(), body.getPort(), body.getRoot(),
                                        "Public", System.currentTimeMillis());
            }
//            sendPublicKey(agent.getIp(), agent.getPort());
            agentRepo.addAgent(agent);
        } else {
            agentRepo.updateStatus(maybeAgent.get().getId(), "connected");
            agentRepo.updateHeartBeat(maybeAgent.get().getId(), System.currentTimeMillis());
//            sendPublicKey(maybeAgent.get().getIp(), maybeAgent.get().getPort());
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

    @PostMapping("/heartbeat")
    public Response heartbeat(@RequestBody HeartBeatRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findByIpPort(body.getIp(), body.getPort());

        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        long newHeartBeat = System.currentTimeMillis();
        agentRepo.updateHeartBeat(maybeAgent.get().getId(), newHeartBeat);

        return Response.good();
    }
}
