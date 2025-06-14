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
public class WorkoutSession {

    /*
    * This entity brings together the individual workout groups and morphs them into a collective unit or 'session'.
    * Each workout session should feature workout groups and have an attached time of completion.
    * */

    @Id
    private long id;

    @NotNull
    String name;

    @NotNull
    String description;

    @NotNull
    LocalDateTime time;
    Status status_id;
    String status;
    String comments;

    @OneToMany
    List<WorkoutGroup> workoutGroups;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
