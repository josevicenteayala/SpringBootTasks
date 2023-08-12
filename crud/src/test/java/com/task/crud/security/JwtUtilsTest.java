package com.task.crud.security;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.jsonwebtoken.Claims;
import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

class JwtUtilsTest {

    private static final String SECRET_KEY = "customSecurityKeyWithCustomInformationWithMoreThan288Bits";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    @Test
    public void testGenerateTokenAndParse() {
        // Prepare a user details object with a username and authorities (roles)
        User userDetails = new User("user123", "password", getAuthorities());

        // Generate a JWT token for the user details
        String token = JwtUtils.generateToken(userDetails, EXPIRATION_TIME, SECRET_KEY);

        // Verify the token is not empty
        assertThat(token).isNotBlank();

        // Parse the token to extract claims
        Claims claims = JwtUtils.parseToken(token, SECRET_KEY);

        // Verify the token subject (username) and roles
        assertThat(claims.getSubject()).isEqualTo("user123");

        String role = JwtUtils.getRoles(claims);

        assertEquals(role, getAuthority());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        // For this test, we create a list of authorities (roles)
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    private String getAuthority() {
        return "ROLE_USER";
    }
}