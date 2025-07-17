package org.project.workouttrackerdemo.repository;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.workouttrackerdemo.model.User;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void findUserByEmail() {
        User sampleUser = User.builder()
                .username("someone")
                .email("someone@email.com")
                .build();

        userRepository.save(sampleUser);
        User storedUser = userRepository.findUserByEmail(sampleUser.getEmail());

        assertEquals(sampleUser, storedUser);
    }

    @Test
    void findUserByUsername() {
        User sampleUser = User.builder()
                .username("someone")
                .email("someone@email.com")
                .build();

        userRepository.save(sampleUser);
        User storedUser = userRepository.findUserByUsername(sampleUser.getUsername());

        assertEquals(sampleUser, storedUser);
    }

    @Test
    void existsByUsername() {
        User sampleUser = User.builder()
                .username("someone")
                .email("someone@email.com")
                .build();

        userRepository.save(sampleUser);

        assertTrue(userRepository.existsByUsername(sampleUser.getUsername()));
    }
}