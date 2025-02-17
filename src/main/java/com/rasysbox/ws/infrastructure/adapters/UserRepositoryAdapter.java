package com.rasysbox.ws.infrastructure.adapters;

import com.rasysbox.ws.application.dtos.UserDto;
import com.rasysbox.ws.domain.models.UserDomain;
import com.rasysbox.ws.domain.repositories.UserRepository;
import com.rasysbox.ws.infrastructure.persistence.entities.UserEntity;
import com.rasysbox.ws.infrastructure.persistence.repositories.JpaUserRepository;
import com.rasysbox.ws.shared.mappers.UserMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.rasysbox.ws.shared.utils.Constants.*;

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
    public UserDomain findByEmail(String email) {
        UserEntity userEntity = jpaUserRepository.findUserEntityByEmail(email);
        if (userEntity == null) {
            log.warn(USER_NOT_FOUND_BY_EMAIL, email);
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }
        log.info(SEARCHING_USER_BY_EMAIL, email);
        return userMapper.entityToDomain(userEntity);
    }

    @Override
    public Optional<UserDomain> findById(Long id) {
        Optional<UserDomain> userDomain = jpaUserRepository.findById(id).map(userMapper::entityToDomain);
        if (userDomain.isEmpty()) {
            log.warn(USER_NOT_FOUND_BY_ID, id);
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }
        log.info(SEARCHING_USER_BY_ID, id);
        return userDomain;
    }

    @Override
    public List<UserDomain> findAllUsers() {
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        if (userEntities.isEmpty()) {
            log.warn(NO_USERS_FOUND);
            throw new IllegalArgumentException(NO_USERS_FOUND);
        }
        log.info(SEARCHING_ALL_USERS);
        return userMapper.entityListToDomainList(jpaUserRepository.findAll());
    }

    @Override
    public UserDomain save(UserDto userDto) {
        UserEntity userEntity = jpaUserRepository.findUserEntityByEmail(userDto.getEmail());
        if (userEntity != null) {
            log.warn(USER_ALREADY_EXISTS_BY_EMAIL, userDto.getEmail());
            throw new IllegalArgumentException("User already exists");
        }
        log.info(SAVING_USER, userDto.getEmail());
        return userMapper.entityToDomain(jpaUserRepository.save(userMapper.dtoToEntity(userDto)));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (!jpaUserRepository.existsById(id)) {
            log.warn(USER_NOT_FOUND_BY_ID, id);
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }
        log.info(DELETING_USER, id);
        jpaUserRepository.deleteById(id);
        return !jpaUserRepository.existsById(id);
    }

    @Override
    public UserDomain update(Long id, UserDto userDto) {
        UserEntity userEntity = jpaUserRepository.findById(id).orElseThrow(() -> {
            log.warn(USER_NOT_FOUND_BY_ID, id);
            return new IllegalArgumentException(USER_NOT_FOUND);
        });
        log.info(UPDATING_USER, id);
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        return userMapper.entityToDomain(jpaUserRepository.save(userEntity));
    }

}
