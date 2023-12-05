package com.example.springtest.services;

import com.example.springtest.dtos.api.UserDTO;
import com.example.springtest.dtos.web.AddModelDto;
import com.example.springtest.dtos.web.AddUserDto;
import com.example.springtest.dtos.web.ShowDetailedUserInfoDto;
import com.example.springtest.dtos.web.ShowUserInfoDto;

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

    BigDecimal getTotalAmount(UUID uuid);

    void addUser(AddUserDto dto);

    List<ShowUserInfoDto> getAllUsers();

    Optional<ShowDetailedUserInfoDto> getDetails(UUID uuid);
}
