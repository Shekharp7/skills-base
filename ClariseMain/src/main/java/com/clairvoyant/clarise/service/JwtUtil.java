package com.clairvoyant.clarise.service;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public String generateToken(String token, String role, String eId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",role);
        claims.put("id", eId);
        return createToken(claims, token);
    }

    public String generateToken(String token, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",role);
        return createToken(claims, token);
    }

    private String createToken(Map<String, Object> claims, String token) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(14);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(token)
                .setExpiration(Date.from(localDateTime.toInstant(ZoneOffset.UTC)))
                .compact();
    }

}
