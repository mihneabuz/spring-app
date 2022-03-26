package com.controller;

import com.model.agent.AgentsResponse;
import com.model.agent.DirectoryRequest;
import com.model.agent.DirectoryResponse;
import com.model.agent.DeleteRequest;
import com.model.Response;
import com.model.agent.FileCreateRequest;
import com.repository.AgentRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// DELETE: used for testing
@CrossOrigin

@RestController
@RequestMapping("/files")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    private static final AgentRepository agentRepo = AgentRepository.get();

    private String getJSONResponse(String url, String jsonRequestBody) {
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

    @GetMapping("/agents")
    public Response showAgents() {
        return new AgentsResponse(agentRepo.getAgents());
    }

    @PostMapping("/dir")
    public Response showDirectory(@RequestBody DirectoryRequest body) {

        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());

        if(maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files";
        String jsonInputString = new JSONObject()
                .put("path", body.getPath())
                .toString();

        return new DirectoryResponse(getJSONResponse(url, jsonInputString));
    }

    @GetMapping("/delete")
    public Response deletePath(@RequestBody DeleteRequest body) {

        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());

        if(maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files/delete";
        String jsonInputString = "{\"path\": \"" + body.getPath() + "\"}";

        System.out.println(getJSONResponse(url, jsonInputString));

        return new DirectoryResponse(getJSONResponse(url, jsonInputString));

    }

    @PostMapping("/create")
    public Response createFile(@RequestBody FileCreateRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files" + "/create";
        String jsonInput = new JSONObject()
                .put("path", body.getPath())
                .put("type", body.getType())
                .put("content", body.getContent())
                .toString();

        // TODO maybe check response
//        String response = getJSONResponse(url, jsonInput);

        return Response.good();
    }
}

