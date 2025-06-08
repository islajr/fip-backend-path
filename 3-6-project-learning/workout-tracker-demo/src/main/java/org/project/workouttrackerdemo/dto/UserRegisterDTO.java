package org.project.workouttrackerdemo.dto;

import org.project.workouttrackerdemo.model.Level;
import org.project.workouttrackerdemo.model.Role;
import org.project.workouttrackerdemo.model.User;

import static org.project.workouttrackerdemo.config.Utilities.validateRole;

public record UserRegisterDTO(
        String username,
        String name,
        String email,
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
