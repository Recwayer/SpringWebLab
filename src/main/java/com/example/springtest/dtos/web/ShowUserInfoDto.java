package com.example.springtest.dtos.web;

import com.example.springtest.models.enums.Role;

import java.io.Serializable;
import java.util.UUID;

public class ShowUserInfoDto implements Serializable {
    private UUID uuid;
    private String username;
    private Role role;

    public ShowUserInfoDto() {
    }

    public ShowUserInfoDto(UUID uuid, String username, Role role) {
        this.uuid = uuid;
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
