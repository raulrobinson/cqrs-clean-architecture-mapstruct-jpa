package com.rasysbox.ws.domain.repositories;

import com.rasysbox.ws.application.dtos.UserDto;
import com.rasysbox.ws.domain.models.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserDomain findByEmail(String email);

    Optional<UserDomain> findById(Long id);
    List<UserDomain> findAllUsers();
    UserDomain save(UserDto userDto);

    Boolean deleteById(Long id);

    UserDomain update(Long id, UserDto userDto);
}
