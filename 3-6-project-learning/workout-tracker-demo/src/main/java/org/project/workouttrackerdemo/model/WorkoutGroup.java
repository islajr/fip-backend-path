package org.project.workouttrackerdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class WorkoutGroup {

    /*
    * This represents a single unit of a workout exercise, featuring reps and sets.
    * A real life example of this would be 3 sets of 20 push-up reps (3 X 20).
    * */

    @Id
    private Long id;

    @OneToOne
    Workout workout;

    int reps;
    int sets;
    int weight_kg;
    Status status_id;
    String status;
    String comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
