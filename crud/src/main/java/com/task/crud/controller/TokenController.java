package com.task.crud.controller;

import com.task.crud.security.JwtUtils;
import com.task.crud.service.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {
    private final UserDetailsServiceImpl userDetailsService;

    public TokenController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String getToken() {
        // For this example, generate and return a JWT token for the hardcoded user "user"
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
        return JwtUtils.generateToken(userDetails);
    }

}
