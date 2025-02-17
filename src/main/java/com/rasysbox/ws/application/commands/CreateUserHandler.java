package com.rasysbox.ws.application.commands;

import com.rasysbox.ws.application.dtos.UserDto;
import com.rasysbox.ws.domain.models.UserDomain;
import com.rasysbox.ws.domain.repositories.UserRepository;
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
