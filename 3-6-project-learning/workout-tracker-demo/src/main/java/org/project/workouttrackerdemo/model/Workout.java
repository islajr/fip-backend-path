package org.project.workouttrackerdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Workout {

    /*
    * This is the cornerstone of the business logic and the most basic entity of this application.
    * It defines a 'workout' and everything it could possibly entail, ranging from name to muscle groups to creation meta-data.
    * A real-life example of this would be push-ups, muscle-ups, etc.
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    MuscleGroup muscle_group_id;
    String muscle_group;
    Category category_id;
    String category;
    Difficulty difficulty_id;
    boolean isUserCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    String difficulty;
    Status status_id;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
