package com.controller;

import com.model.*;
import com.repository.UserRepository;
import com.auth.JwtOps;
import com.auth.exceptions.UnauthorizedException;
import com.entity.Identity;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

// DELETE: used for testing
@CrossOrigin

@RestController
@RequestMapping("/user")
class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final UserRepository userRepo = UserRepository.get();

    @PostMapping("/register")
    public Response register(@RequestBody RegisterRequest body) {
        log.info(body.toString());

        var alreadyExists = userRepo.findByUsername(body.getUsername()).isPresent();

        if (alreadyExists) {
            return Response.bad("Username already in use!");
        } else {
            userRepo.addUser(body.getUsername(), body.getPassword());
            return Response.good();
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest body) {
        log.info(body.toString());

        var maybeUser = userRepo.findByUsername(body.getUsername());

        if (maybeUser.isPresent()) {
            var user = maybeUser.get();

            if (user.getPassword().equals(body.getPassword())) {
                String token = JwtOps.createToken(user);
                return new LoginResponse(token);
            } else {
                return Response.bad("Wrong password!");
            }

        } else {
            return Response.bad("Wrong username!");
        }
    }

    @PostMapping("/updateUsername")
    public Response updateUsername(@RequestHeader("Authorization") String auth,
                                   @RequestBody UpdateUsernameRequest body) {

        log.info(body.toString());

        Identity identity = JwtOps.decodeOrThrow(auth);
        var maybeUser = userRepo.findByID(identity.getId());

        if (!maybeUser.isPresent()) {
            // This is very bad
            log.error("USER ID NOT FOUND IN DB!");
            throw new UnauthorizedException("User id not found");
        }

        if (maybeUser.get().getUsername().equals(body.getNewUsername())) {
            return Response.bad("New username same as current");
        }

        if (userRepo.findByUsername(body.getNewUsername()).isPresent()) {
            return Response.bad("Username already in use!");
        }

        userRepo.updateUsername(identity.getId(), body.getNewUsername());

        return Response.good();
    }

    @PostMapping("/updatePassword")
    public Response udpatePassword(@RequestHeader("Authorization") String auth,
                                   @RequestBody UpdatePasswordRequest body) {

        log.info(body.toString());

        Identity identity = JwtOps.decodeOrThrow(auth);
        var maybeUser = userRepo.findByID(identity.getId());

        if (!maybeUser.isPresent()) {
            // This is very bad
            log.error("USER ID NOT FOUND IN DB!");
            throw new UnauthorizedException("User id not found");
        }

        if (maybeUser.get().getPassword().equals(body.getOldPassword())) {
            userRepo.updatePassword(identity.getId(), body.getNewPassword()); 
            return Response.good();
        } else {
            return Response.bad("Wrong password!"); 
        }
    }

    @DeleteMapping("/deleteUser")
    public Response deleteUser(@RequestHeader("Authorization") String auth,
                               @RequestBody DeleteUserRequest body) {
        
        log.info(body.toString());

        Identity identity = JwtOps.decodeOrThrow(auth);
        var maybeUser = userRepo.findByID(identity.getId());

        if (!maybeUser.isPresent()) {
            // This is very bad
            log.error("USER ID NOT FOUND IN DB!");
            throw new UnauthorizedException("User id not found");
        }

        if (maybeUser.get().getPassword().equals(body.getPassword())) {
            userRepo.deleteUser(identity.getId()); 
            return Response.good();
        } else {
            return Response.bad("Wrong password!"); 
        }
    }

    @GetMapping("/count")
    public long count(@RequestHeader("Authorization") String auth) {
        JwtOps.decodeOrThrow(auth);
        return userRepo.count();
    }
}
