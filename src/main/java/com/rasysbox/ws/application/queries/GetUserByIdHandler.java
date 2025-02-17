package com.rasysbox.ws.application.queries;

import com.rasysbox.ws.domain.models.UserDomain;
import com.rasysbox.ws.domain.repositories.UserRepository;
import com.rasysbox.ws.shared.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdHandler {
    private final UserRepository repository;

    public GetUserByIdHandler(UserRepository repository) {
        this.repository = repository;
    }

    public UserDomain handle(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }
}
