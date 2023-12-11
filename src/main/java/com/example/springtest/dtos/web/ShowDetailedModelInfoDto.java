package com.example.springtest.dtos.web;

import com.example.springtest.models.enums.Category;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ShowDetailedModelInfoDto {
    private UUID uuid;
    private String name;

    private Category category;
    private int startYear;
    private int endYear;
    private String brandName;

    private List<ShowOfferInfoDto> offers;
    private String image_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<ShowOfferInfoDto> getOffers() {
        return offers;
    }

    public void setOffers(List<ShowOfferInfoDto> offers) {
        this.offers = offers;
    }
}
