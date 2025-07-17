package org.project.workouttrackerdemo.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.workouttrackerdemo.dto.UserLoginDTO;
import org.project.workouttrackerdemo.dto.UserRegisterDTO;
import org.project.workouttrackerdemo.dto.UserUpdateDTO;
import org.project.workouttrackerdemo.model.Level;
import org.project.workouttrackerdemo.model.Role;
import org.project.workouttrackerdemo.model.User;
import org.project.workouttrackerdemo.model.UserPrincipal;
import org.project.workouttrackerdemo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
class UserServiceTest {

    @Mock
    private final UserRepository userRepository;

    @Mock
    private final AuthenticationManager authenticationManager;

    @Mock
    private final JwtService jwtService;

    @Mock
    private final MyUserDetailsService myUserDetailsService;

    @Mock
    private final Authentication authentication;

    @Mock
    private final PasswordEncoder passwordEncoder;

    @Mock
    private final UserPrincipal userPrincipal;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, authenticationManager, jwtService, myUserDetailsService, passwordEncoder);
    }

    @AfterEach
    void tearDown() {
        // release resources
    }

    @Test
    void registerUser() {
        UserRegisterDTO sample = new UserRegisterDTO(
                "user",
                "some user",
                "someone@email.com",
                "someonesomeonesomeone",
                "USER",
                "BEGINNER"
        );
        User sampleUser = UserRegisterDTO.setUser(sample);
        String token = "token";
        String refresh = "refresh";
        String responseModel = """
                access token: token
                refresh token: refresh
                """;

        when (jwtService.generateToken(sampleUser.getEmail())).thenReturn(token);
        when (jwtService.generateRefreshToken(sampleUser.getEmail())).thenReturn(refresh);

        ResponseEntity<String> response = userService.registerUser(sample);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseModel, response.getBody());
    }

    @Test
    void loginUser() {
        UserLoginDTO loginDTO = new UserLoginDTO("login@email.com", "password");

        String token = "token";
        String refresh = "refresh";
        String responseModel = """
                    access token: token
                    refresh token: refresh
                    """;

        when (authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.identifier(), loginDTO.password()))).thenReturn(authentication);
        when (authentication.isAuthenticated()).thenReturn(true);
        when (jwtService.generateToken(loginDTO.identifier())).thenReturn(token);
        when (jwtService.generateRefreshToken(loginDTO.identifier())).thenReturn(refresh);

        ResponseEntity<String> response = userService.loginUser(loginDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseModel, response.getBody());
    }

    @Test
    void updateUser() {
        UserUpdateDTO updateDTO = new UserUpdateDTO("name", "someone@email.com", "someone@123");
        User sampleUser = new User(
                1L,
                "name",
                "username",
                "someone@email.com",
                "someone@123",
                "USER",
                Role.USER,
                "BEGINNER",
                Level.BEGINNER,
                LocalDateTime.now(),
                null,
                null,
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        userRepository.save(sampleUser);

        when (SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(updateDTO.name());

        HttpStatus expectedStatus = HttpStatus.OK;
        String expectedBody = "Successfully updated user.";
        ResponseEntity<String> response = userService.updateUser(updateDTO);

        assertNotNull(response);
        assertEquals(expectedStatus, response.getStatusCode());
        assertEquals(expectedBody, response.getBody());
    }

    @Test
    void deleteUser() {
        User sampleUser = new User(
                1L,
                "name",
                "username",
                "user@email.com",
                "user@123",
                "USER",
                Role.USER,
                "BEGINNER",
                Level.BEGINNER,
                LocalDateTime.now(),
                null,
                null,
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        String expectedResponseBody = "Successfully deleted user!";
        HttpStatus expectedStatus = HttpStatus.NO_CONTENT;
        userRepository.save(sampleUser);

        when (userRepository.findById(sampleUser.getId())).thenReturn(Optional.of(sampleUser));
        ResponseEntity<String> response = userService.deleteUser(String.valueOf(sampleUser.getId()));

        verify(userRepository).findById(sampleUser.getId());

        assertNotNull(response);
        assertEquals(expectedStatus, response.getStatusCode());
        assertEquals(expectedResponseBody, response.getBody());
    }

    @Test
    void refreshToken() {
        String refreshToken = "token";
        String access = "access";
        String refresh = "refresh";
        String email = "user@email.com";
        String expectedResponse = """
                    access token: %s
                    refresh token: %s
                    """;

        when(jwtService.extractEmail(refreshToken)).thenReturn(email);
        when(myUserDetailsService.loadUserByUsername(email)).thenReturn(userPrincipal);
        when(jwtService.verifyToken(refreshToken, userPrincipal)).thenReturn(true);
        when(jwtService.generateToken(email)).thenReturn(access);
        when(jwtService.generateRefreshToken(email)).thenReturn(refresh);

        ResponseEntity<String> response = userService.refreshToken();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}