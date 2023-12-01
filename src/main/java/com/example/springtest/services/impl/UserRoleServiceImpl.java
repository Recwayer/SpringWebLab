package com.example.springtest.services.impl;

import com.example.springtest.dtos.api.UserRoleDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.UserRole;
import com.example.springtest.repositories.UserRoleRepository;
import com.example.springtest.services.UserRoleService;
import com.example.springtest.utils.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;
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
    private final ValidationUtil validationUtil;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleDTO register(UserRoleDTO dto) {
        if (!this.validationUtil.isValid(dto)) {
            this.validationUtil
                    .violations(dto)
                    .stream()
                    .map(ConstraintViolation::getMessage).forEach(s -> {
                        System.out.println(s);
                        throw new ClientException.InvalidInputException(s);
                    });
            return null;
        } else {
            UserRole userRole = modelMapper.map(dto, UserRole.class);
            if (userRole.getUuid() == null || get(userRole.getUuid()).isEmpty()) {
                return modelMapper.map(userRoleRepository.saveAndFlush(userRole), UserRoleDTO.class);
            } else {
                throw new ClientException.InvalidInputException("A role with this uuid already exists");
            }
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
    public UserRoleDTO update(UserRoleDTO dto) {
        if (!this.validationUtil.isValid(dto)) {
            this.validationUtil
                    .violations(dto)
                    .stream()
                    .map(ConstraintViolation::getMessage).forEach(s -> {
                        System.out.println(s);
                        throw new ClientException.InvalidInputException(s);
                    });
            return null;
        } else {
            if (userRoleRepository.findById(dto.getUuid()).isPresent()) {
                return modelMapper.map(userRoleRepository.saveAndFlush(modelMapper.map(dto, UserRole.class)), UserRoleDTO.class);
            } else {
                throw new ClientException.NotFoundException("Not Found Role");
            }
        }
    }
}
