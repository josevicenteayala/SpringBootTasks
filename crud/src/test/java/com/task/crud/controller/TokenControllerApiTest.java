package com.task.crud.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.task.crud.security.JwtUtils;
import com.task.crud.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class TokenControllerApiTest {

    @InjectMocks
    private TokenController tokenController;

    @Spy
    private UserDetailsServiceImpl userDetailsService;

    private MockMvc mockMvc;

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

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        Claims claims = JwtUtils.parseToken(result.getResponse().getContentAsString());

        assertAll(
                ()-> assertEquals("crud-security", claims.get(JwtUtils.ISS_CLAIM)),
                ()-> assertEquals("user", claims.getSubject()),
                ()-> assertEquals("ROLE_USER", JwtUtils.getRoles(claims))
        );
    }

}