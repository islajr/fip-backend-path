package org.project.workouttrackerdemo.controller;

import lombok.AllArgsConstructor;
import org.project.workouttrackerdemo.dto.UserLoginDTO;
import org.project.workouttrackerdemo.dto.UserRegisterDTO;
import org.project.workouttrackerdemo.dto.UserUpdateDTO;
import org.project.workouttrackerdemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.registerUser(userRegisterDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.loginUser(userLoginDTO);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(userUpdateDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        return userService.deleteUser(username);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken() {
        return userService.refreshToken();
    }
}
