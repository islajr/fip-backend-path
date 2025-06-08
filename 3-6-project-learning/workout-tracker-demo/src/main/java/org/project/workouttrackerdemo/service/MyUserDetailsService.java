package org.project.workouttrackerdemo.service;

import lombok.AllArgsConstructor;
import org.project.workouttrackerdemo.model.User;
import org.project.workouttrackerdemo.model.UserPrincipal;
import org.project.workouttrackerdemo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.project.workouttrackerdemo.config.Utilities.sortIdentifier;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {

        User user = null;

        switch (sortIdentifier(identifier)) {
            case("email") -> user = userRepository.findUserByEmail(identifier);
            case ("username") -> user = userRepository.findUserByUsername(identifier);
            case null -> throw new NullPointerException("Blank username");  // create new custom exception
            default -> throw new IllegalStateException("Unexpected value: " + sortIdentifier(identifier));  // same as above
        }

        if (user == null) {
            throw new RuntimeException("No such user");  // custom exception for incorrect or non-existent username
        }

        return new UserPrincipal(user);

    }
}
