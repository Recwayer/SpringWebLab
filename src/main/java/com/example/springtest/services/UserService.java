package com.example.springtest.services;

import com.example.springtest.dtos.UserDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserDTO register(UserDTO user);

    List<UserDTO> getAll();

    Optional<UserDTO> get(UUID uuid);

    void delete(UUID uuid);

    UserDTO update(UserDTO user);

    BigDecimal getTotalAmount(UUID uuid);
}
