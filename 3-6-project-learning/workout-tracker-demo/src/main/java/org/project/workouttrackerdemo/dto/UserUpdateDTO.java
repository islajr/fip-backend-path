package org.project.workouttrackerdemo.dto;

public record UserUpdateDTO(
        String username,
        String email,
        String name,
        String password
) {
}
