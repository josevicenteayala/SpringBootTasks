package com.task.crud.service;

import com.task.crud.security.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshTokeExpiration;

    public String generateToken(UserDetails userDetails) {
        return JwtUtils.generateToken(userDetails, jwtExpiration, secretKey);
    }


    public Claims parseToken(String jwt) {
        return JwtUtils.parseToken(jwt, secretKey);
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(jwt);
        return claimsResolver.apply(claims);
    }
}
