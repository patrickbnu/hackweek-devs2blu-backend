package com.api.hackweek.utils;

import com.api.hackweek.exceptions.JwtSecurityException;
import com.api.hackweek.models.user.User;
import com.api.hackweek.utils.constants.Constants;
import com.api.hackweek.utils.constants.ErrorMessages;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private String expiration;

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getLogin())
                    .withClaim("id", String.valueOf(user.getId()))
                    .withExpiresAt(getExpirationTime())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException ex) {
            throw new JwtSecurityException(ErrorMessages.JWT_CREATION_ERROR);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new JwtSecurityException();
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now().plusSeconds(Integer.parseInt(expiration)).toInstant(ZoneOffset.of(Constants.ZONE_OFFSET));
    }

    public Optional<String> getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader(Constants.HEADER_STRING);

        if (header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
            return Optional.empty();
        }

        return Optional.of(header.substring(7));
    }
}
