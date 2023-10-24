package com.example.springtest.services.impl;

import com.example.springtest.dtos.BrandDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.Brand;
import com.example.springtest.repositories.BrandRepository;
import com.example.springtest.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BrandServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public BrandDTO register(BrandDTO brand) {
        Brand b = modelMapper.map(brand, Brand.class);
        if (b.getUuid() == null || get(b.getUuid()).isEmpty()) {
            return modelMapper.map(brandRepository.save(b), BrandDTO.class);
        } else {
            throw new ClientException.InvalidInputException("A brand with this uuid already exists");
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
    public void delete(UUID id) {
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
        }
    }

    @Override
    public BrandDTO update(BrandDTO brand) {
        if (brandRepository.findById(brand.getUuid()).isPresent()) {
            Brand b = modelMapper.map(brand, Brand.class);
            b.setModified(new Date());
            return modelMapper.map(brandRepository.save(b), BrandDTO.class);
        } else {
            throw new ClientException.NotFoundException("Not Found Brand");
        }
    }
}
