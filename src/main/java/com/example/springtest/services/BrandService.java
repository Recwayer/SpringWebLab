package com.example.springtest.services;

import com.example.springtest.dtos.api.BrandDTO;
import com.example.springtest.dtos.web.AddBrandDto;
import com.example.springtest.dtos.web.ShowBrandInfoDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandService {
    BrandDTO register(BrandDTO dto);

    List<BrandDTO> getAll();

    Optional<BrandDTO> get(UUID uuid);

    void delete(UUID uuid);

    BrandDTO update(BrandDTO dto);

    void addBrand(AddBrandDto dto);

    List<ShowBrandInfoDto> getAllBrands();
}
