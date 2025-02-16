package com.rasysbox.mapstruct.infrastructure.persistence.repositories;

import com.rasysbox.mapstruct.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByEmail(String email);
}
