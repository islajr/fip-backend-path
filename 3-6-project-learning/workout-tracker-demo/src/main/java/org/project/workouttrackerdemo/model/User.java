package org.project.workouttrackerdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String username;
    String email;
    String password;
    String role;
    Role role_id;
    String level;
    Level level_id;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
