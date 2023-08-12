package com.task.crud.controller;

import com.task.crud.service.JwtService;
import com.task.crud.service.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
@CrossOrigin("*")
public class TokenController {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    public TokenController(UserDetailsServiceImpl userDetailsService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String getToken() {
        // For this example, generate and return a JWT token for the hardcoded user "user"
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
        return jwtService.generateToken(userDetails);
    }

}
