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
    private String modelName;
    private String sellerUsername;

    public ShowDetailedOfferInfoDto() {
    }

    public ShowDetailedOfferInfoDto(UUID uuid, String description, Engine engine, long mileage, BigDecimal price, Transmission transmission, int year, String modelName, String sellerUsername) {
        this.uuid = uuid;
        this.description = description;
        this.engine = engine;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.modelName = modelName;
        this.sellerUsername = sellerUsername;
    }

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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }
}
