package com.example.springtest.services.impl;

import com.example.springtest.dtos.OfferDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.models.Offer;
import com.example.springtest.repositories.OfferRepository;
import com.example.springtest.services.OfferService;
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
    private OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDTO register(OfferDTO offer) {
        Offer o = modelMapper.map(offer, Offer.class);
        if (o.getUuid() == null || get(o.getUuid()).isEmpty()) {
            return modelMapper.map(offerRepository.save(o), OfferDTO.class);
        } else {
            throw new ClientException.InvalidInputException("A offer with this uuid already exists");
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
    public OfferDTO update(OfferDTO offer) {
        if (offerRepository.findById(offer.getUuid()).isPresent()) {
            Offer o = modelMapper.map(offer, Offer.class);
            o.setModified(new Date());
            return modelMapper.map(offerRepository.save(o), OfferDTO.class);
        } else {
            throw new ClientException.NotFoundException("Not Found Offer");
        }
    }
}
