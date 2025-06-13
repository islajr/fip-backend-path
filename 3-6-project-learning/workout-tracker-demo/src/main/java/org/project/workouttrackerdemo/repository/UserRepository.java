package org.project.workouttrackerdemo.repository;

import org.project.workouttrackerdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
    User findUserByUsername(String username);

    boolean existsByUsername(String username);
}
