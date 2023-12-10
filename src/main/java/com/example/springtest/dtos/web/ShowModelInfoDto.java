package com.example.springtest.dtos.web;

import com.example.springtest.models.enums.Category;

import java.io.Serializable;
import java.util.UUID;

public class ShowModelInfoDto implements Serializable {
    private UUID uuid;
    private String name;
    private Category category;
    private String brandName;

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
}
