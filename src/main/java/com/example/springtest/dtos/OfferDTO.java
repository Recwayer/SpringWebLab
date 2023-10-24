package com.example.springtest.dtos;

import com.example.springtest.models.enums.Engine;
import com.example.springtest.models.enums.Transmission;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class OfferDTO {
   private UUID uuid;
    private Date created;
    private Date modified;
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

    public OfferDTO(UUID uuid, Date created, Date modified, String description, Engine engine, String image_url, long mileage, BigDecimal price, Transmission transmission, int year, UUID modelUuid, UUID sellerUuid) {
        this.uuid = uuid;
        this.created = created;
        this.modified = modified;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
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
                ", created=" + created +
                ", modified=" + modified +
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
