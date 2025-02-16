package com.rasysbox.mapstruct.domain.repositories;

import com.rasysbox.mapstruct.application.dtos.UserDto;
import com.rasysbox.mapstruct.domain.models.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<UserDomain> findById(Long id);
    List<UserDomain> findAllUsers();
    UserDomain save(UserDto userDto);
}
