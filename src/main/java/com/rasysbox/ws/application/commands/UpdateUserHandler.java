package com.rasysbox.ws.application.commands;

import com.rasysbox.ws.application.dtos.UserDto;
import com.rasysbox.ws.domain.models.UserDomain;
import com.rasysbox.ws.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserHandler {
    private final UserRepository repository;

    public UpdateUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    public UserDomain execute(Long id, UserDto userDto) {
        return repository.update(id, userDto);
    }
}
