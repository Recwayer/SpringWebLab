package com.example.springtest.services.impl;

import com.example.springtest.dtos.api.OfferDTO;
import com.example.springtest.dtos.web.*;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.Offer;
import com.example.springtest.repositories.ModelRepository;
import com.example.springtest.repositories.OfferRepository;
import com.example.springtest.repositories.UserRepository;
import com.example.springtest.services.OfferService;
import com.example.springtest.utils.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private OfferRepository offerRepository;
    private ModelRepository modelRepository;
    private UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OfferDTO register(OfferDTO dto) {
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
            Offer offer = modelMapper.map(dto, Offer.class);
            if (offer.getUuid() == null || get(offer.getUuid()).isEmpty()) {
                offer.setCreated(new Date());
                offer.setModified(new Date());
                return modelMapper.map(offerRepository.saveAndFlush(offer), OfferDTO.class);
            } else {
                throw new ClientException.InvalidInputException("A offer with this uuid already exists");
            }
        }
    }

    @Override
    public List<OfferDTO> getAll() {
        return offerRepository.findAll().stream().map(s -> modelMapper.map(s, OfferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<OfferDTO> get(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findById(uuid), OfferDTO.class));
    }

    @Override
    public void delete(UUID uuid) {
        if (offerRepository.findById(uuid).isPresent()) {
            offerRepository.deleteById(uuid);
        }
    }

    @Override
    public OfferDTO update(OfferDTO dto) {
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
            if (offerRepository.findById(dto.getUuid()).isPresent()) {
                Offer offer = modelMapper.map(dto, Offer.class);
                offer.setModified(new Date());
                return modelMapper.map(offerRepository.saveAndFlush(offer), OfferDTO.class);
            } else {
                throw new ClientException.NotFoundException("Not Found Offer");
            }
        }
    }

    @Override
    public void addOffer(AddOfferDto dto) {
        Offer offer = modelMapper.map(dto, Offer.class);
        offer.setCreated(new Date());
        offer.setModified(new Date());
        offer.setModel(modelRepository.findByName(dto.getModelName()).orElse(null));
        offer.setSeller(userRepository.findByUsername(dto.getSellerUserName()).orElse(null));
        offerRepository.saveAndFlush(offer);
    }

    @Override
    public List<ShowOfferInfoDto> getAllOffers() {
        return offerRepository.findAll().stream().map(m -> modelMapper.map(m, ShowOfferInfoDto.class)).toList();
    }

    @Override
    public Optional<ShowDetailedOfferInfoDto> getDetails(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findById(uuid), ShowDetailedOfferInfoDto.class));
    }
}
