package org.project.workouttrackerdemo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.workouttrackerdemo.model.UserPrincipal;
import org.project.workouttrackerdemo.service.JwtService;
import org.project.workouttrackerdemo.service.MyUserDetailsService;
import org.project.workouttrackerdemo.service.TokenService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;
    private final TokenService tokenService;

    public JwtFilter(JwtService jwtService, MyUserDetailsService myUserDetailsService, TokenService tokenService) {
        this.jwtService = jwtService;
        this.myUserDetailsService = myUserDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        final String AUTH_PREFIX = "Bearer ";
        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith(AUTH_PREFIX)) {
            token = authHeader.substring(AUTH_PREFIX.length());
            email = jwtService.extractEmail(token);
        }

        if (!tokenService.isAllowed(token))
            throw new BadCredentialsException("Expired or invalid token!");

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserPrincipal userPrincipal = (UserPrincipal) myUserDetailsService.loadUserByUsername(email);

            if (jwtService.verifyToken(token, userPrincipal)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }


        filterChain.doFilter(request, response);
    }
}
