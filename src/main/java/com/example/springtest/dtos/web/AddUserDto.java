package com.example.springtest.dtos.web;

import com.example.springtest.models.UserRole;
import com.example.springtest.utils.validation.annotations.UniqueUserName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddUserDto {
    @UniqueUserName
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private UserRole role;

    private boolean is_active;

    public AddUserDto() {
    }

    public AddUserDto(String username, String password, String firstName, String lastName, UserRole role, boolean is_active) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.is_active = is_active;
    }

    @NotEmpty(message = "Username cannot be null or empty!")
    @Size(min = 3, message = "Username should be at least 3 characters long!")
    public String getUsername() {
        return username;
    }

    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 3, message = "Password should be at least 3 characters long!")
    public String getPassword() {
        return password;
    }

    @NotEmpty(message = "First name cannot be null or empty!")
    @Size(min = 2, message = "First name should be at least 2 characters long!")
    public String getFirstName() {
        return firstName;
    }

    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 2, message = "Last name should be at least 2 characters long!")
    public String getLastName() {
        return lastName;
    }

    @NotEmpty(message = "Please choose a Role!")
    public UserRole getRole() {
        return role;
    }

    @NotEmpty(message = "Please choose an active!")
    public boolean is_active() {
        return is_active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void set_active(boolean is_active) {
        this.is_active = is_active;
    }
}
