package com.example.springtest.services.impl;

import com.example.springtest.dtos.UserRoleDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.UserRole;
import com.example.springtest.repositories.UserRoleRepository;
import com.example.springtest.services.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final ModelMapper modelMapper;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleDTO register(UserRoleDTO userRole) {
        UserRole ur = modelMapper.map(userRole, UserRole.class);
        if (ur.getUuid() == null || get(ur.getUuid()).isEmpty()) {
            return modelMapper.map(userRoleRepository.save(ur), UserRoleDTO.class);
        } else {
            throw new ClientException.InvalidInputException("A role with this uuid already exists");
        }
    }

    @Override
    public List<UserRoleDTO> getAll() {
        return userRoleRepository.findAll().stream().map(s -> modelMapper.map(s, UserRoleDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserRoleDTO> get(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(userRoleRepository.findById(uuid), UserRoleDTO.class));
    }

    @Override
    public void delete(UUID uuid) {
        if (userRoleRepository.findById(uuid).isPresent()) {
            userRoleRepository.deleteById(uuid);
        }
    }

    @Override
    public UserRoleDTO update(UserRoleDTO userRole) {
        if (userRoleRepository.findById(userRole.getUuid()).isPresent()) {
            return modelMapper.map(userRoleRepository.save(modelMapper.map(userRole, UserRole.class)), UserRoleDTO.class);
        } else {
            throw new ClientException.NotFoundException("Not Found Role");
        }
    }
}
