package org.project.workouttrackerdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class WorkoutPlan {

    /*
    * A workout plan is the culmination of other workout entities.
    * It is the finished product that includes a goal, a plan type, a deadline, and is also tied to a user.
    * A workout plan consists of a series of workout sessions.
    * */

    @Id
    private Long id;

    @NotNull
    String name;

    @NotNull
    String goal;

    @OneToMany
    @JoinColumn(name = "workout_session_id", nullable = false)
    List<WorkoutSession> workoutSession;

    PlanType plan_type_id;
    String plan_type;

    @NotNull
    LocalDateTime start;

    LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    Status status_id;
    String status;
    String comments;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
