package com.example.springtest.services.impl;

import com.example.springtest.dtos.UserDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.User;
import com.example.springtest.repositories.OfferRepository;
import com.example.springtest.repositories.UserRepository;
import com.example.springtest.services.UserService;
import com.example.springtest.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private UserRepository userRepository;
    private OfferRepository offerRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO register(UserDTO dto) {
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
            User user = modelMapper.map(dto, User.class);
            if (user.getUuid() == null || get(user.getUuid()).isEmpty()) {
                user.setCreated(new Date());
                user.setModified(new Date());
                return modelMapper.map(userRepository.saveAndFlush(user), UserDTO.class);
            } else {
                throw new ClientException.InvalidInputException("A user with this uuid already exists");
            }
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
    public UserDTO update(UserDTO dto) {
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
            if (userRepository.findById(dto.getUuid()).isPresent()) {
                User user = modelMapper.map(dto, User.class);
                user.setModified(new Date());
                return modelMapper.map(userRepository.saveAndFlush(user), UserDTO.class);
            } else {
                throw new ClientException.NotFoundException("Not Found User");
            }
        }
    }

    @Override
    public BigDecimal getTotalAmount(UUID uuid) {
        Optional<UserDTO> user = get(uuid);
        BigDecimal total = new BigDecimal(0);
        if (user.isPresent()) {
            total = total.add(offerRepository.findSumPriceBySellerUuid(uuid));
        }
        return total;
    }
}
