package org.project.workouttrackerdemo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

    public ResponseEntity<String> createWorkout() {
        return ResponseEntity.ok("Workout Created");
    }

    public ResponseEntity<String> retrieveWorkout() {
        return ResponseEntity.ok("Here's your workout!");
    }

    public ResponseEntity<String> updateWorkout() {
        return ResponseEntity.ok("Workout Updated!");
    }

    public ResponseEntity<String> deleteWorkout() {
        return ResponseEntity.ok("Workout deleted!");
    }


}
