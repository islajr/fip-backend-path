package org.project.workouttrackerdemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.project.workouttrackerdemo.model.Level;
import org.project.workouttrackerdemo.model.Role;
import org.project.workouttrackerdemo.model.User;

public record UserRegisterDTO(

        @NotNull(message = "username field is required.")
        String username,

        @NotNull(message = "name field is required.")
        String name,

        @NotNull(message = "email field is required.")
        @Email(message = "please provide a valid email address")
        String email,

        @NotNull(message = "password field is required")
        @Size(min = 8, message = "minimum of eight characters")
        String password,

        String role,
        String level
) {
    public static User setUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.username);
        user.setName(userRegisterDTO.name);
        user.setEmail(userRegisterDTO.email);
        user.setPassword(userRegisterDTO.password);
        user.setRole(userRegisterDTO.role.toUpperCase());
        user.setRole_id(Role.valueOf(user.getRole()));
        user.setLevel(userRegisterDTO.level);
        user.setLevel_id(Level.valueOf(user.getLevel()));

        return user;
    }
}
