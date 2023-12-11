package com.example.springtest.services;

import com.example.springtest.dtos.api.UserDTO;
import com.example.springtest.dtos.web.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserDTO register(UserDTO dto);

    List<UserDTO> getAll();

    Optional<UserDTO> get(UUID uuid);

    void delete(UUID uuid);

    UserDTO update(UserDTO dto);

    UpdateUserDto update(UUID uuid, UpdateUserDto dto);

    BigDecimal getTotalAmount(UUID uuid);

    void addUser(AddUserDto dto);


    List<ShowUserInfoDto> getAllUsers();

    Optional<ShowDetailedUserInfoDto> getDetails(UUID uuid);

    Optional<UpdateUserDto> getUpdateUser(UUID uuid);

    void changeActive(UUID uuid);
}
