package org.project.workouttrackerdemo.model;

import jakarta.persistence.*;
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
