package com.rasysbox.mapstruct.application.commands;

import com.rasysbox.mapstruct.application.dtos.UserDto;
import com.rasysbox.mapstruct.domain.models.UserDomain;
import com.rasysbox.mapstruct.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserHandler {
    private final UserRepository repository;

    public CreateUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    public UserDomain execute(UserDto user) {
        return repository.save(user);
    }
}
