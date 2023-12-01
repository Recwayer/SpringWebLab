package com.example.springtest.controllers.rest;

import com.example.springtest.dtos.api.ModelDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/model")
public class ModelRestController {
    private ModelService modelService;


    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/{uuid}")
    public ModelDTO get(@PathVariable UUID uuid) {
        Optional<ModelDTO> modelDTOOptional = modelService.get(uuid);
        if (modelDTOOptional.isPresent()) {
            return modelDTOOptional.get();
        } else {
            throw new ClientException.NotFoundException("Model not found with UUID " + uuid);
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
            throw new ClientException.NotFoundException("Model not found with UUID " + uuid);
        }
    }
}
