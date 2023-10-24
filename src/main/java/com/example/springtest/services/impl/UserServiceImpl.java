package com.example.springtest.services.impl;

import com.example.springtest.dtos.UserDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.User;
import com.example.springtest.repositories.UserRepository;
import com.example.springtest.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO register(UserDTO user) {
        User u = modelMapper.map(user, User.class);
        if (u.getUuid() == null || get(u.getUuid()).isEmpty()) {
            return modelMapper.map(userRepository.save(u), UserDTO.class);
        } else {
            throw new ClientException.InvalidInputException("A user with this uuid already exists");
        }
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(s -> modelMapper.map(s, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> get(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(uuid), UserDTO.class));
    }

    @Override
    public void delete(UUID uuid) {
        if (userRepository.findById(uuid).isPresent()) {
            userRepository.deleteById(uuid);
        }
    }

    @Override
    public UserDTO update(UserDTO user) {
        if (userRepository.findById(user.getUuid()).isPresent()) {
            User u = modelMapper.map(user, User.class);
            u.setModified(new Date());
            return modelMapper.map(userRepository.save(u), UserDTO.class);
        } else {
            throw new ClientException.NotFoundException("Not Found User");
        }
    }
}
