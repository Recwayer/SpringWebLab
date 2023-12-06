package com.example.springtest.dtos.web;

import java.math.BigDecimal;
import java.util.UUID;

public class ShowOfferInfoDto {
    private UUID uuid;
    private BigDecimal price;
    private String sellerUsername;

    public ShowOfferInfoDto() {
    }

    public ShowOfferInfoDto(UUID uuid, BigDecimal price, String sellerUsername) {
        this.uuid = uuid;
        this.price = price;
        this.sellerUsername = sellerUsername;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }
}
