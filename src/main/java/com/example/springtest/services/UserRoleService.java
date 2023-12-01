package com.example.springtest.services;

import com.example.springtest.dtos.api.UserRoleDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService {
    UserRoleDTO register(UserRoleDTO dto);
    List<UserRoleDTO> getAll();
    Optional<UserRoleDTO> get(UUID uuid);
    void delete(UUID uuid);
    UserRoleDTO update(UserRoleDTO dto);
}
