package com.rasysbox.ws.infrastructure.controllers;

import com.rasysbox.ws.application.commands.CreateUserHandler;
import com.rasysbox.ws.application.commands.DeleteUserHandler;
import com.rasysbox.ws.application.commands.UpdateUserHandler;
import com.rasysbox.ws.application.dtos.UserResponseDto;
import com.rasysbox.ws.application.queries.GetAllUsersHandler;
import com.rasysbox.ws.application.queries.GetUserByIdHandler;
import com.rasysbox.ws.application.dtos.UserDto;
import com.rasysbox.ws.domain.models.UserDomain;
import com.rasysbox.ws.shared.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${controller.properties.base-path}/users")
@Tag(name = "Users", description = "User operations")
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
    @Operation(summary = "Find user by ID", description = "Find user by ID")
    public UserResponseDto getUserById(@PathVariable Long id) {
        UserDomain userDomain = UserMapper.INSTANCE.dtoToDomain(UserMapper.INSTANCE.domainToDto(getUserByIdHandler.handle(id)));
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(id);
        userResponseDto.setName(userDomain.getName());
        userResponseDto.setEmail(userDomain.getEmail());

        return userResponseDto;
    }

    @GetMapping("/find-all-users")
    @Operation(summary = "Find all users", description = "Find all users")
    public List<UserDto> findAllUsers() {
        return UserMapper.INSTANCE.domainListToDtoList(getAllUsersHandler.handle());
    }

    @PostMapping("/create-user")
    @Operation(summary = "Create user", description = "Create user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return UserMapper.INSTANCE.domainToDto(createUserHandler.execute(userDto));
    }

    @PutMapping("/update-user/{id}")
    @Operation(summary = "Update user", description = "Update user")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return UserMapper.INSTANCE.domainToDto(updateUserHandler.execute(id, userDto));
    }

    @DeleteMapping("/delete-user/{id}")
    @Operation(summary = "Delete user", description = "Delete user")
    public Boolean deleteUser(@PathVariable Long id) {
        return deleteUserHandler.execute(id);
    }

}
