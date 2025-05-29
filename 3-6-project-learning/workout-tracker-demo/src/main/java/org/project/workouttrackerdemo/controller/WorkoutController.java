package org.project.workouttrackerdemo.controller;

import lombok.AllArgsConstructor;
import org.project.workouttrackerdemo.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping()
    public ResponseEntity<String> createWorkout() {
        return workoutService.createWorkout();
    }

    @GetMapping()
    public ResponseEntity<String> retrieveWorkout() {
        return workoutService.retrieveWorkout();
    }

    @PutMapping
    public ResponseEntity<String> updateWorkout() {
        return workoutService.updateWorkout();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteWorkout() {
        return workoutService.deleteWorkout();
    }


}
