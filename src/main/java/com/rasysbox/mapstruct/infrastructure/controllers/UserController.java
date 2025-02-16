package com.rasysbox.mapstruct.infrastructure.controllers;

import com.rasysbox.mapstruct.application.commands.CreateUserHandler;
import com.rasysbox.mapstruct.application.queries.GetAllUsersHandler;
import com.rasysbox.mapstruct.application.queries.GetUserByIdHandler;
import com.rasysbox.mapstruct.application.dtos.UserDto;
import com.rasysbox.mapstruct.shared.mappers.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("${controller.properties.base-path}/users")
public class UserController {
    private final GetUserByIdHandler getUserByIdHandler;
    private final GetAllUsersHandler getAllUsersHandler;
    private final CreateUserHandler createUserHandler;

    public UserController(GetUserByIdHandler getUserByIdHandler, GetAllUsersHandler getAllUsersHandler, CreateUserHandler createUserHandler) {
        this.getUserByIdHandler = getUserByIdHandler;
        this.getAllUsersHandler = getAllUsersHandler;
        this.createUserHandler = createUserHandler;
    }

    @GetMapping("/find-user-by-id/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return UserMapper.INSTANCE.domainToDto(getUserByIdHandler.handle(id));
    }

    @GetMapping("/find-all-users")
    public List<UserDto> findAllUsers() {
        return UserMapper.INSTANCE.domainListToDtoList(getAllUsersHandler.handle());
    }

    @PostMapping("/create-user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return UserMapper.INSTANCE.domainToDto(createUserHandler.execute(userDto));
    }
}
