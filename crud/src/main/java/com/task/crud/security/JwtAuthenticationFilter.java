package com.task.crud.security;

import com.task.crud.service.JwtService;
import com.task.crud.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final int BEARER_LENGTH = 7;
    private final UserDetailsServiceImpl userDetailsService;

    private final JwtService jwtService;

    public JwtAuthenticationFilter(UserDetailsServiceImpl userDetailsService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        System.out.println("111111111111111111111111111111111111111 " + servletPath);
        if (servletPath.contains("/tokens")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = request.getHeader("Authorization");
        if (Objects.isNull(token) || isNotBearer(token)) {
           throw new ServletException("The user requires authentication");
        } else {
            String jwt = extractJwt(token);
            String user = jwtService.extractUsername(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(user);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(userDetails.getPassword());
            System.out.println(userDetails.getUsername());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        filterChain.doFilter(request, response);
    }

    private String extractJwt(String token) {
        return token.substring(BEARER_LENGTH);
    }

    private static boolean isNotBearer(String token) {
        return !token.startsWith("Bearer ");
    }
}