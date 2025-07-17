package org.project.workouttrackerdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany
    List<Workout> created_workouts;

    @OneToMany
    List<WorkoutGroup> created_workout_groups;

    @OneToMany
    List<WorkoutSession> created_workout_sessions;

    @OneToMany
    List<WorkoutPlan> created_workout_plans;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
