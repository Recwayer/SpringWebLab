package com.example.springtest.services;

import com.example.springtest.dtos.api.ModelDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelService {
    ModelDTO register(ModelDTO dto);
    List<ModelDTO> getAll();
    Optional<ModelDTO> get(UUID uuid);
    void delete(UUID uuid);
    ModelDTO update(ModelDTO dto);
}
