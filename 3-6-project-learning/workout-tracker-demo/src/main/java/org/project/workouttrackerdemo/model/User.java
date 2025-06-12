package org.project.workouttrackerdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    String name;

    @NotNull
    String username;
    @NotNull
    String email;

    @NotNull
    String password;

    @NotNull
    String role;

    @NotNull
    Role role_id;

    @NotNull
    String level;

    @NotNull
    Level level_id;

    @NotNull
    LocalDateTime last_seen;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
