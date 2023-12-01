package com.example.springtest.controllers.rest;

import com.example.springtest.dtos.api.BrandDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/brand")
public class BrandRestController {
    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{uuid}")
    public BrandDTO get(@PathVariable UUID uuid) {
        Optional<BrandDTO> brandDTOOptional = brandService.get(uuid);
        if (brandDTOOptional.isPresent()) {
            return brandDTOOptional.get();
        } else {
            throw new ClientException.NotFoundException("Brand not found with UUID " + uuid);
        }
    }

    @GetMapping("/all")
    public Iterable<BrandDTO> getAll() {
        return brandService.getAll();
    }

    @PostMapping()
    public BrandDTO create(@RequestBody BrandDTO brandDTO) {
        return brandService.register(brandDTO);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        get(uuid);
        brandService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    public BrandDTO update(@PathVariable UUID uuid, @RequestBody BrandDTO brandDTO) {
        if (brandService.get(uuid).isPresent()) {
            brandDTO.setUuid(uuid);
            return brandService.update(brandDTO);
        } else {
            throw new ClientException.NotFoundException("Brand not found with UUID " + uuid);
        }
    }
}
