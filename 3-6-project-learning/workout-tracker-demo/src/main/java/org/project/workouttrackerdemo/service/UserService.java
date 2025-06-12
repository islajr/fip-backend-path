package org.project.workouttrackerdemo.service;

import lombok.AllArgsConstructor;
import org.project.workouttrackerdemo.dto.UserLoginDTO;
import org.project.workouttrackerdemo.dto.UserRegisterDTO;
import org.project.workouttrackerdemo.dto.UserUpdateDTO;
import org.project.workouttrackerdemo.model.User;
import org.project.workouttrackerdemo.model.UserPrincipal;
import org.project.workouttrackerdemo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.project.workouttrackerdemo.config.Utilities.isUserAuthorized;
import static org.project.workouttrackerdemo.dto.UserRegisterDTO.setUser;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;

    public ResponseEntity<String> registerUser(UserRegisterDTO userRegisterDTO) {
        User user;
        user = setUser(userRegisterDTO);
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("""
                access token: %s
                refresh token: %s
                """.formatted(jwtService.generateToken(user.getEmail()), jwtService.generateRefreshToken(user.getEmail()))); // maybe create a response DTO later.
    }

    public ResponseEntity<String> loginUser(UserLoginDTO userLoginDTO) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.identifier(), userLoginDTO.password()));

        if (authentication.isAuthenticated()) {
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            return ResponseEntity.ok("""
                    access token: %s
                    refresh token: %s
                    """.formatted(jwtService.generateToken(principal.getEmail()), jwtService.generateRefreshToken(principal.getEmail())));
        }
        throw new BadCredentialsException("Incorrect username and/or password");    // custom exception later, maybe?
    }

    public ResponseEntity<String> updateUser(UserUpdateDTO updateDTO) {
        /*
        * currently only allowed to update the name, email, or password.
        * */
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(username);

        if (user != null) {
            if (!Objects.equals(updateDTO.name(), user.getName()) && updateDTO.name() != null) {
                user.setName(updateDTO.name());
            }
            if (!Objects.equals(updateDTO.email(), user.getEmail()) && updateDTO.email() != null) {
                user.setEmail(updateDTO.email());
            }
            if (!Objects.equals(updateDTO.password(), user.getPassword()) && updateDTO.password() != null) {
                user.setPassword(updateDTO.password());
            }

            userRepository.save(user);
            return ResponseEntity.ok("Successfully updated user.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user!");

    }

    public ResponseEntity<String> deleteUser(String username) {
        if (!isUserAuthorized(username))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You cannot complete this action!");
        if (userRepository.existsByUsername(username)) {
            User user = userRepository.findUserByUsername(username);
            userRepository.delete(user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted user!");
        }
        throw new UsernameNotFoundException("No such user!");   // customize exception later.
    }

    public ResponseEntity<String> refreshToken() {

        String identifier = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = ((UserPrincipal) myUserDetailsService.loadUserByUsername(identifier)).getEmail();

        return ResponseEntity.status(HttpStatus.OK).body("""
                    access token: %s
                    refresh token: %s
                    """.formatted(jwtService.generateToken(email), jwtService.generateRefreshToken(email)));
    }
}
