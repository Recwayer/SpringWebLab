package com.example.springtest.controllers;

import com.example.springtest.dtos.UserDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{uuid}")
    public UserDTO get(@PathVariable UUID uuid) {
        Optional<UserDTO> userDTOOptional = userService.get(uuid);
        if (userDTOOptional.isPresent()) {
            return userDTOOptional.get();
        } else {
            throw new ClientException.NotFoundException("User not found with UUID "+ uuid);
        }
    }

    @GetMapping("/all")
    public Iterable<UserDTO> getAll() {
        return userService.getAll();
    }

    @PostMapping()
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        get(uuid);
        userService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    public UserDTO update(@PathVariable UUID uuid, @RequestBody UserDTO userDTO) {
        if (userService.get(uuid).isPresent()) {
            userDTO.setUuid(uuid);
            return userService.update(userDTO);
        } else {
            throw new ClientException.NotFoundException("User not found with UUID "+ uuid);
        }
    }
}
