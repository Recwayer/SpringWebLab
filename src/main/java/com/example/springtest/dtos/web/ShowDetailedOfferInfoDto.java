package com.example.springtest.dtos.web;

import com.example.springtest.models.enums.Engine;
import com.example.springtest.models.enums.Transmission;

import java.math.BigDecimal;
import java.util.UUID;

public class ShowDetailedOfferInfoDto {
    private UUID uuid;
    private String description;
    private Engine engine;
    private long mileage;
    private BigDecimal price;
    private Transmission transmission;
    private int year;
    private ShowModelInfoDto model;
    private String sellerUsername;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ShowModelInfoDto getModel() {
        return model;
    }

    public void setModel(ShowModelInfoDto model) {
        this.model = model;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }
}
