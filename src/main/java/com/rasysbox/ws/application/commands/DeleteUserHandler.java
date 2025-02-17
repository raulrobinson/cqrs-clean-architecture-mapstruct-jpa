package com.rasysbox.ws.application.commands;

import com.rasysbox.ws.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserHandler {
    private final UserRepository repository;

    public DeleteUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    public Boolean execute(Long id) {
        return repository.deleteById(id);
    }
}
