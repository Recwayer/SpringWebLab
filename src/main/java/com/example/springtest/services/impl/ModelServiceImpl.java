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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableCaching
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
    @CacheEvict(value = {"models", "popularModels","offers"}, allEntries = true)
    public void delete(UUID uuid) {
        if (modelRepository.findById(uuid).isPresent()) {
            modelRepository.deleteById(uuid);
        }
    }

    @Override
    @CacheEvict(value = "models", allEntries = true)
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
    @CacheEvict(value = "models", allEntries = true)
    public UpdateModelDto update(UUID uuid, UpdateModelDto dto) {
        Optional<Model> optional = modelRepository.findById(uuid);
        if (optional.isPresent()) {
            Model model = modelMapper.map(dto, Model.class);
            model.setUuid(uuid);
            model.setModified(new Date());
            model.setBrand(brandRepository.findByName(dto.getBrandName()).orElse(null));
            return modelMapper.map(modelRepository.saveAndFlush(model), UpdateModelDto.class);
        } else {
            throw new ClientException.NotFoundException("Not Found Model");
        }
    }

    @Override
    @CacheEvict(value = {"models", "popularModels"}, allEntries = true)
    public void addModel(AddModelDto dto) {
        Model model = modelMapper.map(dto, Model.class);
        model.setCreated(new Date());
        model.setModified(new Date());
        model.setBrand(brandRepository.findByName(dto.getBrandName()).orElse(null));
        modelRepository.saveAndFlush(model);
    }

    @Override
    @Cacheable("models")
    public List<ShowModelInfoDto> getAllModels() {
        return modelRepository.findAll().stream().map(m -> modelMapper.map(m, ShowModelInfoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "models",key = "#sort")
    public List<ShowModelInfoDto> getAllModels(Sort sort) {
        return modelRepository.findAll(sort).stream().map(m -> modelMapper.map(m, ShowModelInfoDto.class)).collect(Collectors.toList());
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
    @Cacheable(value = "models", key = "#searchQuery")
    public List<ShowModelInfoDto> searchModels(String searchQuery) {
        return modelRepository.findModelsByNameLike("%" + searchQuery + "%").stream().map(m -> modelMapper.map(m, ShowModelInfoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "models", key = "#searchQuery+'_'+#sort")
    public List<ShowModelInfoDto> searchModels(String searchQuery, Sort sort) {
        return modelRepository.findModelsByNameLike("%" + searchQuery + "%", sort).stream().map(m -> modelMapper.map(m, ShowModelInfoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "popularModels")
    public List<ShowModelInfoDto> popularModels() {
        List<UUID> popularModelIds = modelRepository.findPopularModelsByOfferCount().stream().limit(3)
                .map(array -> (UUID) array[0])
                .toList();

        return modelRepository.findAllById(popularModelIds)
                .stream()
                .map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());

    }
}
