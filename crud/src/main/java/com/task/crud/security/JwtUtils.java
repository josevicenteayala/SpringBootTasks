package com.task.crud.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtils {

    public static final String CRUD_SECURITY = "crud-security";
    public static final String ISS_CLAIM = "iss";

    public static String generateToken(UserDetails userDetails, Long expirationTime, String secretKey) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put(ISS_CLAIM, CRUD_SECURITY);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static Claims parseToken(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public static String getRoles(Claims claims) {
        return Optional.ofNullable(claims.get("roles"))
                .filter(List.class::isInstance)
                .map(List.class::cast)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .filter(Map.class::isInstance)
                .map(Map.class::cast)
                .map(map -> map.get("authority"))
                .map(Object::toString)
                .orElse(null);
    }
}
