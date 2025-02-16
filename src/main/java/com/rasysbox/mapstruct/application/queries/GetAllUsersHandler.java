package com.rasysbox.mapstruct.application.queries;

import com.rasysbox.mapstruct.domain.models.UserDomain;
import com.rasysbox.mapstruct.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersHandler {
    private final UserRepository repository;

    public GetAllUsersHandler(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserDomain> handle() {
        return repository.findAllUsers();
    }
}
