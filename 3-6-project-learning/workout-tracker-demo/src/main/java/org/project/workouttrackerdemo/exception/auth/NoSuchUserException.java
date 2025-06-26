package org.project.workouttrackerdemo.exception.auth;

public class NoSuchUserException extends RuntimeException {
    String message;

    public NoSuchUserException(String message) {
        this.message = message;
    }
}
