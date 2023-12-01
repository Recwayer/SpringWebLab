package com.example.springtest.dtos.api;

import com.example.springtest.models.enums.Role;

import java.util.UUID;

public class UserRoleDTO {
    private UUID uuid;
    private Role role;

    public UserRoleDTO() {
    }

    public UserRoleDTO(UUID uuid, Role role) {
        this.uuid = uuid;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "uuid=" + uuid +
                ", role=" + role +
                '}';
    }
}
