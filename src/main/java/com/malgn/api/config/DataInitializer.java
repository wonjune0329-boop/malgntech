package com.malgn.api.config;

import com.malgn.api.entity.Role;
import com.malgn.api.entity.User;
import com.malgn.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("user1").isEmpty()) {
            userRepository.save(User.builder()
                    .username("user1")
                    .password("1234")
                    .role(Role.USER)
                    .build());
        }

        if (userRepository.findByUsername("admin").isEmpty()) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password("admin1234")
                    .role(Role.ADMIN)
                    .build());
        }

        if (userRepository.findByUsername("user2").isEmpty()) {
            userRepository.save(User.builder()
                    .username("user2")
                    .password("1234")
                    .role(Role.USER)
                    .build());
        }

    }
}