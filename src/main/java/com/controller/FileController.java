package com.controller;

import com.auth.JwtOps;
import com.auth.exceptions.UnauthorizedException;
import com.entity.Identity;
import com.entity.Product;
import com.model.agent.AgentsResponse;
import com.model.agent.DirectoryRequest;
import com.model.agent.DirectoryResponse;
import com.model.product.CreateProductRequest;
import com.model.product.DeleteProductRequest;
import com.model.product.GetProductsResponse;
import com.model.Response;
import com.model.product.UpdateProductRequest;
import com.model.user.DeleteUserRequest;
import com.model.user.LoginRequest;
import com.model.user.LoginResponse;
import com.model.user.RegisterRequest;
import com.model.user.UpdatePasswordRequest;
import com.model.user.UpdateUsernameRequest;
import com.model.user.UserInfoResponse;
import com.repository.AgentRepository;
import com.repository.ProductRepository;
import com.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

// DELETE: used for testing
@CrossOrigin

@RestController
@RequestMapping("/files")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    private static final AgentRepository agentRepo = AgentRepository.get();

    @GetMapping("/agents")
    public Response showAgents() {

        return new AgentsResponse(agentRepo.getAgents());
    }

    @GetMapping("/dir")
    public Response showDirectory(@RequestBody DirectoryRequest body) {

        var maybeAgent = agentRepo.findById(body.getId());

        if(maybeAgent.isEmpty()) {
            return Response.bad("No such agent");
        }

        String ip = maybeAgent.get().getIp();
        ip = "192.168.25.1";
        String port = maybeAgent.get().getPort();
        port = "3000";

        URL url;
        HttpURLConnection con;

        try {
            url = new URL("http://" + ip + ":" + port + "/files");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            Map<String, String> parameters = new HashMap<>();

            parameters.put("path", body.getPath());

            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json");

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(getParamsString(parameters));
            out.flush();
            out.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        return Response.good();
    }

    public String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

}

