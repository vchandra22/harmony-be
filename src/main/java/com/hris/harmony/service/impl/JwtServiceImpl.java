package com.hris.harmony.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hris.harmony.constant.Constant;
import com.hris.harmony.entity.UserAccount;
import com.hris.harmony.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class JwtServiceImpl implements JwtService {
    private final String JWT_SECRET;
    private final Long JWT_EXPIRATION;
    private final String ISSUER;
    private final Algorithm algorithm;
    
    public JwtServiceImpl(
            @Value("${harmony.jwt.secret_key}") String jwtSecret,
            @Value("${harmony.jwt.expirationInSecond}") Long jwtExpiration,
            @Value("${harmony.jwt.issuer}") String issuer
            ) {
        JWT_SECRET = jwtSecret;
        JWT_EXPIRATION = jwtExpiration;
        ISSUER = issuer;
        algorithm = Algorithm.HMAC512(jwtSecret);
    }

    @Override
    public String generateToken(UserAccount userAccount) {
        try {
            return JWT.create()
                    .withSubject(String.valueOf(userAccount.getId()))
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(JWT_EXPIRATION))
                    .withClaim("roles", userAccount.getAuthorities().stream().map((role) -> role.getAuthority()).toList())
                    .withIssuer(ISSUER)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constant.ERROR_CREATING_JWT);
        }
    }

    @Override
    public boolean verifyToken(String bearerToken) {
        try {
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            String jwtToken = extractJwtFromBearerToken(bearerToken);
            jwtVerifier.verify(jwtToken);
            
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
    
    private String extractJwtFromBearerToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        
        return "";
    }

    @Override
    public String getUserIdByToken(String bearerToken) {
        try {
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            String jwtToken = extractJwtFromBearerToken(bearerToken);
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
            
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
    
    
}
