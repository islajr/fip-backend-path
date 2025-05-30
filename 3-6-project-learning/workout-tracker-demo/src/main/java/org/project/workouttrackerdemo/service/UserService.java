package org.project.workouttrackerdemo.service;

import lombok.AllArgsConstructor;
import org.project.workouttrackerdemo.config.Utilities;
import org.project.workouttrackerdemo.dto.UserLoginDTO;
import org.project.workouttrackerdemo.dto.UserRegisterDTO;
import org.project.workouttrackerdemo.dto.UserUpdateDTO;
import org.project.workouttrackerdemo.model.User;
import org.project.workouttrackerdemo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.project.workouttrackerdemo.config.Utilities.*;
import static org.project.workouttrackerdemo.dto.UserRegisterDTO.setUser;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> registerUser(UserRegisterDTO userRegisterDTO) {
        User user;
        user = setUser(userRegisterDTO);

        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created user"); // maybe create a response DTO later.
    }

    public ResponseEntity<String> loginUser(UserLoginDTO userLoginDTO) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.identifier(), userLoginDTO.password()));

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok("Successfully logged-in");
        }
        throw new BadCredentialsException("Incorrect username and/or password");    // custom exception later, maybe?
    }

    public ResponseEntity<String> updateUser(UserUpdateDTO updateDTO) {
        String username = getIdentifier();
        User user;
        if (username != null) {
            user = userRepository.findUserByUsername(username);

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden: Please login first");
    }

    public ResponseEntity<String> deleteUser() {
        return null;
    }
}
