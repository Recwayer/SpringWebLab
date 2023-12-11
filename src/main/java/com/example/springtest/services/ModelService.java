package com.example.springtest.services;

import com.example.springtest.dtos.api.ModelDTO;
import com.example.springtest.dtos.web.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelService {
    ModelDTO register(ModelDTO dto);

    List<ModelDTO> getAll();

    Optional<ModelDTO> get(UUID uuid);

    void delete(UUID uuid);

    ModelDTO update(ModelDTO dto);

    UpdateModelDto update(UUID uuid, UpdateModelDto dto);

    void addModel(AddModelDto dto);

    List<ShowModelInfoDto> getAllModels();

    List<ShowModelInfoDto> getAllModels(Sort sort);

    Optional<ShowDetailedModelInfoDto> getDetails(UUID uuid);

    Optional<UpdateModelDto> getUpdateModel(UUID uuid);

    List<ShowModelInfoDto> searchModels(String searchQuery);

    List<ShowModelInfoDto> searchModels(String searchQuery, Sort sort);

    List<ShowModelInfoDto> popularModels();
}
