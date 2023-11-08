package com.example.springtest.models;

import com.example.springtest.models.enums.Category;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    private String name;

    private Category category;
    private String image_url;
    private int startYear;
    private int endYear;

    private Brand brand;

    private List<Offer> offers;

    protected Model() {
    }

    public Model(String name, Category category, String image_url, int startYear, int endYear, Brand brand, List<Offer> offers) {
        this.name = name;
        this.category = category;
        this.image_url = image_url;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
        this.offers = offers;
    }

    public String getName() {
        return this.name;
    }
    @Enumerated(EnumType.ORDINAL)
    public Category getCategory() {
        return this.category;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public int getEndYear() {
        return this.endYear;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public Brand getBrand() {
        return this.brand;
    }
    @OneToMany(mappedBy = "model",cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    public List<Offer> getOffers() {
        return this.offers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
