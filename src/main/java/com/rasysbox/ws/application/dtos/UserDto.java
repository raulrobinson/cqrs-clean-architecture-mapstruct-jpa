package com.rasysbox.ws.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDto {
    @Schema(description = "User name", example = "John Doe")
    private String name;

    @Schema(description = "User email", example = "john.doe@email.com")
    private String email;

    public UserDto() {
    }

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
