package org.project.workouttrackerdemo.config;

import org.project.workouttrackerdemo.model.Role;
import org.project.workouttrackerdemo.model.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utilities {

    public static String sortIdentifier(String identifier) {

        if (!identifier.isBlank()){

            if (identifier.contains("@") && identifier.endsWith(".com")) {  // should fix this later.
                return "email";
            }

            return "username";
        }

        return null;
    }

    public static boolean validateRole(String provided) {
        for (Role role : Role.values()) {
            if (role.name().equals(provided.toUpperCase()))
                return true;
        }

        return false;
    }

    public static String getIdentifier() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userPrincipal.getUsername();
    }
}
