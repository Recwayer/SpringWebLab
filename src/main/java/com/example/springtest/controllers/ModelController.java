package com.example.springtest.controllers;

import com.example.springtest.dtos.ModelDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.services.ModelService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/model")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/{uuid}")
    public ModelDTO get(@PathVariable UUID uuid) {
        Optional<ModelDTO> modelDTOOptional = modelService.get(uuid);
        if (modelDTOOptional.isPresent()) {
            return modelDTOOptional.get();
        } else {
            throw new ClientException.NotFoundException("Model not found with UUID "+ uuid);
        }
    }

    @GetMapping("/all")
    public Iterable<ModelDTO> getAll() {
        return modelService.getAll();
    }

    @PostMapping()
    public ModelDTO create(@RequestBody ModelDTO modelDTO) {
        return modelService.register(modelDTO);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        get(uuid);
        modelService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    public ModelDTO update(@PathVariable UUID uuid, @RequestBody ModelDTO modelDTO) {
        if (modelService.get(uuid).isPresent()) {
            modelDTO.setUuid(uuid);
            return modelService.update(modelDTO);
        } else {
            throw new ClientException.NotFoundException("Model not found with UUID "+ uuid);
        }
    }
}
