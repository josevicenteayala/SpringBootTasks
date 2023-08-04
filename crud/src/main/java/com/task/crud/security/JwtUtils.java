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
    private static final String SECRET_KEY = "customSecurityKeyWithCustomInformationWithMoreThan288Bits";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String CRUD_SECURITY = "crud-security";
    public static final String ISS_CLAIM = "iss";

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put(ISS_CLAIM, CRUD_SECURITY);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
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
