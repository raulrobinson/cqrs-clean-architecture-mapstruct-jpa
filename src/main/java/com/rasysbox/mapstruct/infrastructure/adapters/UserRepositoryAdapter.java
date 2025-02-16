package com.rasysbox.mapstruct.infrastructure.adapters;

import com.rasysbox.mapstruct.application.dtos.UserDto;
import com.rasysbox.mapstruct.domain.models.UserDomain;
import com.rasysbox.mapstruct.domain.repositories.UserRepository;
import com.rasysbox.mapstruct.infrastructure.persistence.entities.UserEntity;
import com.rasysbox.mapstruct.infrastructure.persistence.repositories.JpaUserRepository;
import com.rasysbox.mapstruct.shared.mappers.UserMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserRepositoryAdapter.class);
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDomain> findById(Long id) {
        Optional<UserDomain> userDomain = jpaUserRepository.findById(id).map(userMapper::entityToDomain);
        if (userDomain.isEmpty()) {
            log.warn("User not found by ID: {}", id);
            throw new IllegalArgumentException("User not found");
        }
        log.info("Searching user by ID: {}", id);
        return userDomain;
    }

    @Override
    public List<UserDomain> findAllUsers() {
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        if (userEntities.isEmpty()) {
            log.warn("No users found");
            throw new IllegalArgumentException("No users found");
        }
        log.info("Searching all users");
        return userMapper.entityListToDomainList(jpaUserRepository.findAll());
    }

    @Override
    public UserDomain save(UserDto userDto) {
        UserEntity userEntity = jpaUserRepository.findUserEntityByEmail(userDto.getEmail());
        if (userEntity != null) {
            log.warn("User already exists: {}", userDto.getEmail());
            throw new IllegalArgumentException("User already exists");
        }
        log.info("Saving user: {}", userDto.getEmail());
        return userMapper.entityToDomain(jpaUserRepository.save(userMapper.dtoToEntity(userDto)));
    }
}
