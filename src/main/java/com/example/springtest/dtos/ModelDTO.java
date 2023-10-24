package com.example.springtest.dtos;

import com.example.springtest.models.enums.Category;
import java.util.Date;
import java.util.UUID;

public class ModelDTO {
    private UUID uuid;
    private Date created;
    private Date modified;
    private String name;
    private Category category;
    private String image_url;
    private int startYear;
    private int endYear;
    private UUID brandUuid;

    public ModelDTO() {
    }

    public ModelDTO(UUID uuid, Date created, Date modified, String name, Category category, String image_url, int startYear, int endYear, UUID brandUuId) {
        this.uuid = uuid;
        this.created = created;
        this.modified = modified;
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
                ", created=" + created +
                ", modified=" + modified +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", image_url='" + image_url + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", brandUuid=" + brandUuid +
                '}';
    }
}
