package com.auth;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.auth.exceptions.TokenExpiredException;
import com.auth.exceptions.UnauthorizedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.entity.Identity;
import com.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtOps {

    private static final Logger log = LoggerFactory.getLogger(JwtOps.class);
    private static final Algorithm algorithm = Algorithm.HMAC256("secret");
    private static final long millisInADay = 1000 * 60 * 60 * 24;

    private static Date afterDays(int days) {
        return new Date(System.currentTimeMillis() + (days * millisInADay));
    }

    public static String createToken(User user) {
        var payload = Map.of(
            "id", user.getId(), 
            "username", user.getUsername(),
            "level", user.getLevel());

        String token = JWT.create()
            .withExpiresAt(afterDays(3))
            .withPayload(payload)
            .sign(algorithm);

        return token;
    }

    public static Optional<Identity> decodeToken(String token) {
        Identity identity;

        try {
            DecodedJWT decoded = JWT.decode(token);

            identity = new Identity(
                decoded.getClaim("id").asString(),
                decoded.getClaim("username").asString(),
                decoded.getClaim("level").asInt(),
                decoded.getExpiresAt().getTime()
            );
        } catch (Exception e) {
            log.warn("Bad auth token: " + e.toString());
            return Optional.empty(); 
        }

        return Optional.of(identity);
    }

    public static Optional<Identity> decodeAuth(String auth) {
        var splits = auth.split(" ");

        if (splits.length != 2) {
            log.warn("Bad auth string; word count != 2");
            return Optional.empty();
        }

        if (!splits[0].equals("Bearer")) {
            log.warn("Bad auth string; first word != Bearer");
            return Optional.empty();
        }

        return decodeToken(splits[1]);
    }

    public static Identity decodeOrThrow(String auth) throws RuntimeException {
        var maybeIdentity = decodeAuth(auth);

        if (maybeIdentity.isEmpty()) {
            throw new UnauthorizedException("Cannot decode Token");
        }

        if (maybeIdentity.get().getExpiresAt() < System.currentTimeMillis()) {
            log.warn("JWT Token is expired");
            throw new TokenExpiredException("JWT Token is expired");
        }

        return maybeIdentity.get();
    }
}
