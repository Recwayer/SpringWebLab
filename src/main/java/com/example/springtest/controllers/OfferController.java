package com.example.springtest.controllers;

import com.example.springtest.dtos.OfferDTO;
import com.example.springtest.exceptions.ClientException;
import com.example.springtest.services.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/offer")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{uuid}")
    public OfferDTO get(@PathVariable UUID uuid) {
        Optional<OfferDTO> offerDTOOptional = offerService.get(uuid);
        if (offerDTOOptional.isPresent()) {
            return offerDTOOptional.get();
        } else {
            throw new ClientException.NotFoundException("Offer not found with UUID "+ uuid);
        }

    }

    @GetMapping("/all")
    public Iterable<OfferDTO> getAll() {
        return offerService.getAll();
    }

    @PostMapping()
    public OfferDTO create(@RequestBody OfferDTO offerDTO) {
        return offerService.register(offerDTO);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        get(uuid);
        offerService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    public OfferDTO update(@PathVariable UUID uuid, @RequestBody OfferDTO offerDTO) {
        if (offerService.get(uuid).isPresent()) {
            offerDTO.setUuid(uuid);
            return offerService.update(offerDTO);
        } else {
            throw new ClientException.NotFoundException("Offer not found with UUID "+ uuid);
        }
    }
}
