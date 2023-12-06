package com.example.springtest.services;

import com.example.springtest.dtos.api.BrandDTO;
import com.example.springtest.dtos.web.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandService {
    BrandDTO register(BrandDTO dto);

    List<BrandDTO> getAll();

    Optional<BrandDTO> get(UUID uuid);

    void delete(UUID uuid);

    BrandDTO update(BrandDTO dto);

    UpdateBrandDto update(UUID uuid, UpdateBrandDto dto);

    void addBrand(AddBrandDto dto);

    List<ShowBrandInfoDto> getAllBrands();

    Optional<ShowDetailedBrandInfoDto> getDetails(UUID uuid);

    Optional<UpdateBrandDto> getUpdateBrand(UUID uuid);
}
