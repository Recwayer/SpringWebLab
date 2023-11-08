package com.example.springtest.services.impl;

import com.example.springtest.dtos.ModelDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.Model;
import com.example.springtest.repositories.ModelRepository;
import com.example.springtest.services.ModelService;
import com.example.springtest.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelDTO register(ModelDTO dto) {
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
            Model model = modelMapper.map(dto, Model.class);
            if (model.getUuid() == null || get(model.getUuid()).isEmpty()) {
                model.setCreated(new Date());
                model.setModified(new Date());
                return modelMapper.map(modelRepository.save(model), ModelDTO.class);
            } else {
                throw new ClientException.InvalidInputException("A model with this uuid already exists");
            }
        }
    }

    @Override
    public List<ModelDTO> getAll() {
        return modelRepository.findAll().stream().map(s -> modelMapper.map(s, ModelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<ModelDTO> get(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(uuid), ModelDTO.class));
    }

    @Override
    public void delete(UUID uuid) {
        if (modelRepository.findById(uuid).isPresent()) {
            modelRepository.deleteById(uuid);
        }
    }

    @Override
    public ModelDTO update(ModelDTO dto) {
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
            if (modelRepository.findById(dto.getUuid()).isPresent()) {
                Model model = modelMapper.map(dto, Model.class);
                model.setModified(new Date());
                return modelMapper.map(modelRepository.save(model), ModelDTO.class);
            } else {
                throw new ClientException.NotFoundException("Not Found Model");
            }
        }
    }
}
