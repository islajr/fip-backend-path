package org.project.workouttrackerdemo.dto;

import jakarta.validation.constraints.NotNull;

public record UserLoginDTO(

        @NotNull(message = "please provide a username!")
        String identifier,

        @NotNull(message = "please provide a password")
        String password
) {
}
