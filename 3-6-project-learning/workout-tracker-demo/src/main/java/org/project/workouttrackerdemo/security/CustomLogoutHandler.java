package org.project.workouttrackerdemo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.project.workouttrackerdemo.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenService tokenService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String AUTH_PREFIX = "Bearer ";
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith(AUTH_PREFIX)) {
            String token = authHeader.substring(AUTH_PREFIX.length());
            tokenService.disallowToken(token);
            // invalidate refresh token later.
        }

        // invalidate any sessions.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // clear security context
        SecurityContextHolder.clearContext();

    }
}
