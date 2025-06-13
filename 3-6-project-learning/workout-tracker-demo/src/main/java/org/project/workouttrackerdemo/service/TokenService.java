package org.project.workouttrackerdemo.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenService {

    private final Set<String> disallowedTokens = new HashSet<>();

    public int disallowToken(String token) {
        disallowedTokens.add(token);
        return 0;
    }

    public boolean isAllowed(String token) {
        return !disallowedTokens.contains(token);
    }

}
