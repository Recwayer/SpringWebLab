package com.example.springtest.controllers.Rest;

import com.example.springtest.dtos.UserDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{uuid}")
    public UserDTO get(@PathVariable UUID uuid) {
        Optional<UserDTO> userDTOOptional = userService.get(uuid);
        if (userDTOOptional.isPresent()) {
            return userDTOOptional.get();
        } else {
            throw new ClientException.NotFoundException("User not found with UUID " + uuid);
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
            throw new ClientException.NotFoundException("User not found with UUID " + uuid);
        }
    }

    @GetMapping("/{uuid}/total")
    public BigDecimal getAmountTotal(@PathVariable UUID uuid) {
        if (userService.get(uuid).isPresent()) {
            return userService.getTotalAmount(uuid);
        } else {
            throw new ClientException.NotFoundException("User not found with UUID " + uuid);
        }
    }
}
