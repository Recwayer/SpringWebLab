package com.example.springtest.dtos.web;

import com.example.springtest.models.enums.Role;

import java.util.UUID;

public class ShowDetailedUserInfoDto {
    private UUID uuid;
    private String username;
    private String firstName;
    private String lastName;
    private boolean is_active;
    private Role role;
    private String image_url;

    public ShowDetailedUserInfoDto() {
    }

    public ShowDetailedUserInfoDto(UUID uuid, String username, String firstName, String lastName, boolean is_active, Role role) {
        this.uuid = uuid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.is_active = is_active;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
