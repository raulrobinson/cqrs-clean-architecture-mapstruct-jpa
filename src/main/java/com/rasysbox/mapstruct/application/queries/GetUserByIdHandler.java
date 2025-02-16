package com.rasysbox.mapstruct.application.queries;

import com.rasysbox.mapstruct.domain.models.UserDomain;
import com.rasysbox.mapstruct.domain.repositories.UserRepository;
import com.rasysbox.mapstruct.shared.exceptions.UserNotFoundException;
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
