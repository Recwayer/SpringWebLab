package com.example.springtest.repositories;

import com.example.springtest.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    @Query("select sum(o.price) from Offer o where o.seller.uuid= :uuid")
    public BigDecimal findSumPriceBySellerUuid(@Param("uuid")UUID uuid);
}
