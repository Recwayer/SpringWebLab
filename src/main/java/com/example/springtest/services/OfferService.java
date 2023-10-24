package com.example.springtest.services;


import com.example.springtest.dtos.OfferDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferService {
    OfferDTO register(OfferDTO offer);
    List<OfferDTO> getAll();
    Optional<OfferDTO> get(UUID uuid);
    void delete(UUID uuid);
    OfferDTO update(OfferDTO offer);
}
