package com.example.springtest.services.impl;

import com.example.springtest.dtos.api.ModelDTO;
import com.example.springtest.dtos.web.AddModelDto;
import com.example.springtest.dtos.web.ShowDetailedModelInfoDto;
import com.example.springtest.dtos.web.ShowModelInfoDto;
import com.example.springtest.dtos.web.UpdateModelDto;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.Model;
import com.example.springtest.repositories.BrandRepository;
import com.example.springtest.repositories.ModelRepository;
import com.example.springtest.services.ModelService;
import com.example.springtest.utils.validation.ValidationUtil;
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

    private BrandRepository brandRepository;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
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
                return modelMapper.map(modelRepository.saveAndFlush(model), ModelDTO.class);
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
                return modelMapper.map(modelRepository.saveAndFlush(model), ModelDTO.class);
            } else {
                throw new ClientException.NotFoundException("Not Found Model");
            }
        }
    }

    @Override
    public UpdateModelDto update(UUID uuid, UpdateModelDto dto) {
        Optional<Model> optional = modelRepository.findById(uuid);
        if (optional.isPresent()) {
            Model model = modelMapper.map(dto, Model.class);
            model.setUuid(uuid);
            model.setModified(new Date());
            model.setBrand(brandRepository.findByName(dto.getBrandName()).get());
            return modelMapper.map(modelRepository.saveAndFlush(model), UpdateModelDto.class);
        } else {
            throw new ClientException.NotFoundException("Not Found Model");
        }
    }

    @Override
    public void addModel(AddModelDto dto) {
        Model model = modelMapper.map(dto, Model.class);
        model.setCreated(new Date());
        model.setModified(new Date());
        model.setBrand(brandRepository.findByName(dto.getBrandName()).orElse(null));
        modelRepository.saveAndFlush(model);
    }

    @Override
    public List<ShowModelInfoDto> getAllModels() {
        return modelRepository.findAll().stream().map(m -> modelMapper.map(m, ShowModelInfoDto.class)).toList();
    }

    @Override
    public Optional<ShowDetailedModelInfoDto> getDetails(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(uuid), ShowDetailedModelInfoDto.class));
    }

    @Override
    public Optional<UpdateModelDto> getUpdateModel(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(uuid), UpdateModelDto.class));
    }

    @Override
    public List<ShowModelInfoDto> searchModels(String searchQuery) {
        return modelRepository.findModelsByNameLike("%" + searchQuery + "%").stream().map(m -> modelMapper.map(m, ShowModelInfoDto.class)).toList();
    }
}
