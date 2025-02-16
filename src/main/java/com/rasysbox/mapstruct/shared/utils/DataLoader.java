package com.rasysbox.mapstruct.shared.utils;

import com.rasysbox.mapstruct.infrastructure.persistence.entities.UserEntity;
import com.rasysbox.mapstruct.infrastructure.persistence.repositories.JpaUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final JpaUserRepository jpaUserRepository;

    public DataLoader(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<UserEntity> users = new ArrayList<>();

        UserEntity user1 = new UserEntity();
        user1.setName("Alice Johnson");
        user1.setEmail("alice.johnson@email.com");

        UserEntity user2 = new UserEntity();
        user2.setName("Bob Smith");
        user2.setEmail("bob.smith@email.com");

        users.add(user1);
        users.add(user2);

        jpaUserRepository.saveAll(users);
        System.out.println("📌 Users inserted in the database");
    }
}
