package com.example.springtest.controllers;

import com.example.springtest.dtos.UserRoleDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.services.UserRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/{uuid}")
    public UserRoleDTO get(@PathVariable UUID uuid) {
        Optional<UserRoleDTO> userRoleDTOOptional = userRoleService.get(uuid);
        if (userRoleDTOOptional.isPresent()) {
            return userRoleDTOOptional.get();
        } else {
            throw new ClientException.NotFoundException("Role not found with UUID "+ uuid);
        }
    }

    @GetMapping("/all")
    public Iterable<UserRoleDTO> getAll() {
        return userRoleService.getAll();
    }

    @PostMapping()
    public UserRoleDTO create(@RequestBody UserRoleDTO userRoleDTO) {
        return userRoleService.register(userRoleDTO);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        get(uuid);
        userRoleService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    public UserRoleDTO update(@PathVariable UUID uuid, @RequestBody UserRoleDTO userRoleDTO) {
        if (userRoleService.get(uuid).isPresent()) {
            userRoleDTO.setUuid(uuid);
            return userRoleService.update(userRoleDTO);
        } else {
            throw new ClientException.NotFoundException("Role not found with UUID "+ uuid);
        }
    }
}
