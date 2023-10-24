package com.example.springtest.services;

import com.example.springtest.dtos.UserRoleDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService {
    UserRoleDTO register(UserRoleDTO userRole);
    List<UserRoleDTO> getAll();
    Optional<UserRoleDTO> get(UUID uuid);
    void delete(UUID uuid);
    UserRoleDTO update(UserRoleDTO userRole);
}
