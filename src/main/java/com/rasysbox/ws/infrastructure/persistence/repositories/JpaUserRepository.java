package com.rasysbox.ws.infrastructure.persistence.repositories;

import com.rasysbox.ws.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByEmail(String email);
}
