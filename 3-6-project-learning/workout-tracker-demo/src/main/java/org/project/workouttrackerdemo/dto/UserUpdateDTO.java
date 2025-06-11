package org.project.workouttrackerdemo.dto;

public record UserUpdateDTO(
        String name,
        String email,
        String password
) {
}
