package com.task.crud.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.task.crud.security.JwtUtils;
import com.task.crud.service.JwtService;
import com.task.crud.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class TokenControllerApiTest {

    @InjectMocks
    private TokenController tokenController;

    @Spy
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private JwtService jwtService;

    private MockMvc mockMvc;

    private static final String SECRET_KEY = "customSecurityKeyWithCustomInformationWithMoreThan288Bits";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(tokenController).build();
    }

    @Test
    public void testGetToken() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tokens")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpc3MiOiJjcnVkLXNlY3VyaXR5Iiwic3ViIjoidXNlciIsImlhdCI6MTY5MTE0NDIwOSwiZXhwIjoxNjkyMDA4MjA5fQ.1YEyCcbxVVeYlJTbCMcae_jybKXA_DYskwS9nm37wVA";
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn(token);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Claims claims = JwtUtils.parseToken(contentAsString, SECRET_KEY);

        assertAll(
                ()-> assertEquals("crud-security", claims.get(JwtUtils.ISS_CLAIM)),
                ()-> assertEquals("user", claims.getSubject()),
                ()-> assertEquals("ROLE_USER", JwtUtils.getRoles(claims))
        );
    }

}