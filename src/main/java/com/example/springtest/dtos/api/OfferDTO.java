package com.example.springtest.dtos.api;

import com.example.springtest.models.enums.Engine;
import com.example.springtest.models.enums.Transmission;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class OfferDTO {
   private UUID uuid;
    private String description;
    private Engine engine;
    private String image_url;
    private long mileage;
    private BigDecimal price;
    private Transmission transmission;
    private int year;
    private UUID modelUuid;
    private UUID sellerUuid;

    public OfferDTO() {
    }

    public OfferDTO(UUID uuid, String description, Engine engine, String image_url, long mileage, BigDecimal price, Transmission transmission, int year, UUID modelUuid, UUID sellerUuid) {
        this.uuid = uuid;
        this.description = description;
        this.engine = engine;
        this.image_url = image_url;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.modelUuid = modelUuid;
        this.sellerUuid = sellerUuid;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    @Min(value = 0, message = "Mileage must be more than 0!")
    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }
    @Min(value = 0, message = "Price must be more than 0!")
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
    @Min(value = 0, message = "Year must be more than 0!")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public UUID getModelUuid() {
        return modelUuid;
    }

    public void setModelUuid(UUID modelUuid) {
        this.modelUuid = modelUuid;
    }

    public UUID getSellerUuid() {
        return sellerUuid;
    }

    public void setSellerUuid(UUID sellerUuid) {
        this.sellerUuid = sellerUuid;
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
                "uuid=" + uuid +
                ", description='" + description + '\'' +
                ", engine=" + engine +
                ", image_url='" + image_url + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmission=" + transmission +
                ", year=" + year +
                ", modelUuid=" + modelUuid +
                ", sellerUuid=" + sellerUuid +
                '}';
    }
}
