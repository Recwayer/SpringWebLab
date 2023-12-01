package com.example.springtest.dtos.api;

import com.example.springtest.models.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.UUID;

public class ModelDTO {
    private UUID uuid;
    private String name;
    private Category category;
    private String image_url;
    private int startYear;
    private int endYear;
    private UUID brandUuid;

    public ModelDTO() {
    }

    public ModelDTO(UUID uuid, String name, Category category, String image_url, int startYear, int endYear, UUID brandUuId) {
        this.uuid = uuid;
        this.name = name;
        this.category = category;
        this.image_url = image_url;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brandUuid = brandUuId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Name length must be more than two characters!")
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Min(value = 0, message = "StartYear must be more than 0!")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    @Min(value = 0, message = "EndYear must be more than 0!")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public UUID getBrandUuid() {
        return brandUuid;
    }

    public void setBrandUuid(UUID brandUuid) {
        this.brandUuid = brandUuid;
    }

    @Override
    public String toString() {
        return "ModelDTO{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", image_url='" + image_url + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", brandUuid=" + brandUuid +
                '}';
    }
}
