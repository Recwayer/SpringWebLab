package com.example.springtest.services.impl;

import com.example.springtest.dtos.api.BrandDTO;
import com.example.springtest.dtos.web.AddBrandDto;
import com.example.springtest.dtos.web.ShowBrandInfoDto;
import com.example.springtest.dtos.web.ShowDetailedBrandInfoDto;
import com.example.springtest.dtos.web.UpdateBrandDto;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.Brand;
import com.example.springtest.repositories.BrandRepository;
import com.example.springtest.services.BrandService;
import com.example.springtest.utils.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class BrandServiceImpl implements BrandService {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;

    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @CacheEvict(value = "brands", allEntries = true)
    public BrandDTO register(BrandDTO dto) {
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
            Brand brand = modelMapper.map(dto, Brand.class);
            if (brand.getUuid() == null || get(brand.getUuid()).isEmpty()) {
                brand.setCreated(new Date());
                brand.setModified(new Date());
                return modelMapper.map(brandRepository.saveAndFlush(brand), BrandDTO.class);
            } else {
                throw new ClientException.InvalidInputException("A brand with this uuid already exists");
            }
        }
    }

    @Override
    public List<BrandDTO> getAll() {
        return brandRepository.findAll().stream().map(s -> modelMapper.map(s, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDTO> get(UUID id) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findById(id), BrandDTO.class));
    }

    @Override
    @CacheEvict(value = "brands", allEntries = true)
    public void delete(UUID id) {
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
        }
    }

    @Override
    @CacheEvict(value = "brands", allEntries = true)
    public BrandDTO update(BrandDTO dto) {
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
            if (brandRepository.findById(dto.getUuid()).isPresent()) {
                Brand brand = modelMapper.map(dto, Brand.class);
                brand.setModified(new Date());
                return modelMapper.map(brandRepository.saveAndFlush(brand), BrandDTO.class);
            } else {
                throw new ClientException.NotFoundException("Not Found Brand");
            }
        }
    }

    @Override
    @CacheEvict(value = "brands", allEntries = true)
    public UpdateBrandDto update(UUID uuid, UpdateBrandDto dto) {
        Optional<Brand> optional = brandRepository.findById(uuid);
        if (optional.isPresent()) {
            Brand brand = modelMapper.map(dto, Brand.class);
            brand.setUuid(uuid);
            brand.setModified(new Date());
            return modelMapper.map(brandRepository.saveAndFlush(brand), UpdateBrandDto.class);
        } else {
            throw new ClientException.NotFoundException("Not Found Brand");
        }
    }

    @Override
    @CacheEvict(value = "brands", allEntries = true)
    public void addBrand(AddBrandDto dto) {
        Brand brand = modelMapper.map(dto, Brand.class);
        brand.setCreated(new Date());
        brand.setModified(new Date());
        brandRepository.saveAndFlush(brand);
    }

    @Override
    @Cacheable("brands")
    public List<ShowBrandInfoDto> getAllBrands() {
        return brandRepository.findAll().stream().map(b -> modelMapper.map(b, ShowBrandInfoDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<ShowDetailedBrandInfoDto> getDetails(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findById(uuid), ShowDetailedBrandInfoDto.class));
    }

    public Optional<UpdateBrandDto> getUpdateBrand(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findById(uuid), UpdateBrandDto.class));
    }


}
