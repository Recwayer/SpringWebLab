package com.example.springtest.dtos.web;

import com.example.springtest.models.enums.Engine;
import com.example.springtest.models.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateOfferDto {
    private UUID uuid;
    private String description;
    private Engine engine;
    private long mileage;
    private BigDecimal price;
    private Transmission transmission;
    private int year;
    private String modelName;
    private String sellerUsername;

    public UpdateOfferDto() {
    }

    public UpdateOfferDto(UUID uuid, String description, Engine engine, long mileage, BigDecimal price, Transmission transmission, int year, String modelName, String sellerUsername) {
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

    @NotEmpty(message = "Description must not be null or empty!")
    @Size(min = 5, message = "Description must be at least 5 characters!")
    public String getDescription() {
        return description;
    }

    @NotNull(message = "Please choose an engine!")
    public Engine getEngine() {
        return engine;
    }

    @NotNull(message = "Mileage cannot be null or empty!")
    @Min(value = 1, message = "Mileage must be a positive number!")
    public long getMileage() {
        return mileage;
    }

    @NotNull(message = "Price cannot be null or empty!")
    @Min(value = 1, message = "Price must be a positive number!")
    public BigDecimal getPrice() {
        return price;
    }

    @NotNull(message = "Please choose a transmission!")
    public Transmission getTransmission() {
        return transmission;
    }

    @NotNull(message = "Year cannot be null or empty!")
    @Min(value = 1, message = "Year must be a positive number!")
    public int getYear() {
        return year;
    }

    @NotNull(message = "Please choose a model!")
    public String getModelName() {
        return modelName;
    }

    @NotNull(message = "Please choose a seller!")
    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
