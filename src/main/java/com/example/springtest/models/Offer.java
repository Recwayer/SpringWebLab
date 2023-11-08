package com.example.springtest.models;

import com.example.springtest.models.enums.Engine;
import com.example.springtest.models.enums.Transmission;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    private String description;

    private Engine engine;
    private String image_url;
    private long mileage;
    private BigDecimal price;

    private Transmission transmission;
    private int year;
    private Model model;
    private User seller;
    protected Offer() {
    }

    public Offer(String description, Engine engine, String image_url, long mileage, BigDecimal price, Transmission transmission, int year, Model model, User seller) {
        this.description = description;
        this.engine = engine;
        this.image_url = image_url;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.model = model;
        this.seller = seller;
    }

    public String getDescription() {
        return this.description;
    }
    @Enumerated(EnumType.ORDINAL)
    public Engine getEngine() {
        return this.engine;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public long getMileage() {
        return this.mileage;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
    @Enumerated(EnumType.ORDINAL)
    public Transmission getTransmission() {
        return this.transmission;
    }

    public int getYear() {
        return this.year;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public Model getModel() {
        return this.model;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public User getSeller() {
        return this.seller;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public void setModel(Model model) {
        this.model = model;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
