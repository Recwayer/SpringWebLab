package com.example.springtest.services;


import com.example.springtest.dtos.api.OfferDTO;
import com.example.springtest.dtos.web.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferService {
    OfferDTO register(OfferDTO dto);
    List<OfferDTO> getAll();
    Optional<OfferDTO> get(UUID uuid);
    void delete(UUID uuid);
    OfferDTO update(OfferDTO dto);
    UpdateOfferDto update(UUID uuid, UpdateOfferDto dto);
    void addOffer(AddOfferDto dto);

    List<ShowOfferInfoDto> getAllOffers();

    Optional<ShowDetailedOfferInfoDto> getDetails(UUID uuid);
    Optional<UpdateOfferDto> getUpdateOffer(UUID uuid);
}
