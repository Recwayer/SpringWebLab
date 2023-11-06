package com.example.springtest.services.impl;

import com.example.springtest.dtos.ModelDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.Model;
import com.example.springtest.repositories.ModelRepository;
import com.example.springtest.services.ModelService;
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
    private ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelDTO register(ModelDTO model) {
        Model m = modelMapper.map(model, Model.class);
        if (m.getUuid() == null || get(m.getUuid()).isEmpty()) {
            return modelMapper.map(modelRepository.save(m), ModelDTO.class);
        } else {
            throw new ClientException.InvalidInputException("A model with this uuid already exists");
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
    public ModelDTO update(ModelDTO model) {
        if (modelRepository.findById(model.getUuid()).isPresent()) {
            Model m = modelMapper.map(model, Model.class);
            m.setModified(new Date());
            return modelMapper.map(modelRepository.save(m), ModelDTO.class);
        } else {
            throw new ClientException.NotFoundException("Not Found Model");
        }
    }
}
