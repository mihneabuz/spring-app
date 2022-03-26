package com.controller;

import com.auth.JwtOps;
import com.entity.Identity;
import com.model.Response;
import com.model.file.requests.*;
import com.model.file.responses.*;
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

    private boolean isValid(String auth, String owner) {
        if (!owner.equals("Public")) {
            if (auth != null) {
                Identity identity = JwtOps.decodeOrThrow(auth);

                return identity.getId().equals(owner);
            } else {
                return false;
            }
        }

        return true;
    }

    @GetMapping("/agents")
    public Response showAgents(@RequestHeader(value="Authorization", required = false) String auth) {
        if (auth != null) {
            return new ShowAgentsResponse(agentRepo.getUserAgents(auth));
        }
        return new ShowAgentsResponse(agentRepo.getPublicAgents());
    }

    @PostMapping("/dir")
    public Response showDirectory(@RequestHeader(value="Authorization", required = false) String auth,
                                  @RequestBody ShowDirectoryRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        if (!isValid(auth, maybeAgent.get().getOwner())) {
            return Response.bad("User not authorised!");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files";
        String jsonInputString = new JSONObject()
                .put("path", body.getPath())
                .toString();

        return new ShowDirectoryResponse(getJSONResponse(url, jsonInputString));
    }

    @PostMapping("/create")
    public Response createFile(@RequestHeader(value="Authorization", required = false) String auth,
                               @RequestBody CreateFileRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        if (!isValid(auth, maybeAgent.get().getOwner())) {
            return Response.bad("User not authorised!");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files" + "/create";
        JSONObject jsonInput = new JSONObject()
                .put("path", body.getPath())
                .put("type", body.getType());

        if (body.hasContent()) {
            jsonInput.put("content", body.getContent());
        }
        String jsonInputString = jsonInput.toString();

        JSONObject jsonResponse = new JSONObject(getJSONResponse(url, jsonInputString));
        if (jsonResponse.get("success").toString().equals("false")) {
            return Response.bad(jsonResponse.get("message").toString());
        }
        return Response.good();
    }

    @PostMapping("/delete")
    public Response deletePath(@RequestHeader(value="Authorization", required = false) String auth,
                               @RequestBody DeletePathRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        if (!isValid(auth, maybeAgent.get().getOwner())) {
            return Response.bad("User not authorised!");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files" + "/delete";
        String jsonInputString = new JSONObject()
                .put("path", body.getPath())
                .toString();

        JSONObject jsonResponse = new JSONObject(getJSONResponse(url, jsonInputString));
        if (jsonResponse.get("success").toString().equals("false")) {
            return Response.bad(jsonResponse.get("message").toString());
        }
        return Response.good();
    }

    @PostMapping("/search")
    public Response searchPath(@RequestHeader(value="Authorization", required = false) String auth,
                               @RequestBody SearchPathRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        if (!isValid(auth, maybeAgent.get().getOwner())) {
            return Response.bad("User not authorised!");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files" + "/search";
        String jsonInputString = new JSONObject()
                .put("pattern", body.getPattern())
                .toString();

        return new SearchPathResponse(getJSONResponse(url, jsonInputString));
    }

    @PostMapping("/content")
    public Response showContent(@RequestHeader(value="Authorization", required = false) String auth,
                                @RequestBody ShowContentRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        if (!isValid(auth, maybeAgent.get().getOwner())) {
            return Response.bad("User not authorised!");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files" + "/content";
        String jsonInputString = new JSONObject()
                .put("path", body.getPath())
                .toString();

        return new ShowContentResponse(getJSONResponse(url, jsonInputString));
    }

    @PostMapping("/procs")
    public Response showProcs(@RequestHeader(value="Authorization", required = false) String auth,
                              @RequestBody ShowProcsRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        if (!isValid(auth, maybeAgent.get().getOwner())) {
            return Response.bad("User not authorised!");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/procs";
        JSONObject jsonInput = new JSONObject();

        if (body.hasCount()) {
            jsonInput.put("count", body.getCount());
        }

        if (body.hasSortBy()) {
            jsonInput.put("orderBy", body.getOrderBy());
        }

        String jsonInputString = jsonInput.toString();

        return new ShowProcsResponse(getJSONResponse(url, jsonInputString));
    }

    @PostMapping("/download")
    public Response downloadFile(@RequestHeader(value="Authorization", required = false) String auth,
                                 @RequestBody DownloadFileRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        if (!isValid(auth, maybeAgent.get().getOwner())) {
            return Response.bad("User not authorised!");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files" + "/download";
        String jsonInputString = new JSONObject()
                .put("path", body.getPath())
                .toString();

        return new DownloadFileResponse(getJSONResponse(url, jsonInputString));
    }

    @PostMapping("/upload")
    public Response uploadFile(@RequestHeader(value="Authorization", required = false) String auth,
                               @RequestBody UploadFileRequest body) {
        log.info(body.toString());

        var maybeAgent = agentRepo.findById(body.getId());
        if (maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        if (!isValid(auth, maybeAgent.get().getOwner())) {
            return Response.bad("User not authorised!");
        }

        String ip = maybeAgent.get().getIp();
        String port = maybeAgent.get().getPort();

        String url = "http://" + ip + ":" + port + "/files" + "/upload";
        String jsonInputString = new JSONObject()
                .put("path", body.getPath())
                .put("base64file", body.getBase64file())
                .toString();

        JSONObject jsonResponse = new JSONObject(getJSONResponse(url, jsonInputString));
        if (jsonResponse.get("success").toString().equals("false")) {
            return Response.bad(jsonResponse.get("message").toString());
        }
        return Response.good();
    }
}

