package com.rasysbox.ws.infrastructure.controllers;

import com.rasysbox.ws.application.commands.CreateUserHandler;
import com.rasysbox.ws.application.commands.DeleteUserHandler;
import com.rasysbox.ws.application.commands.UpdateUserHandler;
import com.rasysbox.ws.application.queries.GetAllUsersHandler;
import com.rasysbox.ws.application.queries.GetUserByIdHandler;
import com.rasysbox.ws.application.dtos.UserDto;
import com.rasysbox.ws.shared.mappers.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${controller.properties.base-path}/users")
public class UserController {
    private final GetUserByIdHandler getUserByIdHandler;
    private final GetAllUsersHandler getAllUsersHandler;
    private final CreateUserHandler createUserHandler;
    private final UpdateUserHandler updateUserHandler;
    private final DeleteUserHandler deleteUserHandler;

    public UserController(GetUserByIdHandler getUserByIdHandler, GetAllUsersHandler getAllUsersHandler, CreateUserHandler createUserHandler, UpdateUserHandler updateUserHandler, DeleteUserHandler deleteUserHandler) {
        this.getUserByIdHandler = getUserByIdHandler;
        this.getAllUsersHandler = getAllUsersHandler;
        this.createUserHandler = createUserHandler;
        this.updateUserHandler = updateUserHandler;
        this.deleteUserHandler = deleteUserHandler;
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

    @PutMapping("/update-user/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return UserMapper.INSTANCE.domainToDto(updateUserHandler.execute(id, userDto));
    }

    @DeleteMapping("/delete-user/{id}")
    public Boolean deleteUser(@PathVariable Long id) {
        return deleteUserHandler.execute(id);
    }
}
