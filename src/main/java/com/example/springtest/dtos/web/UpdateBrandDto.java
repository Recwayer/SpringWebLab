package com.example.springtest.dtos.web;

import com.example.springtest.utils.validation.annotations.UniqueBrandName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UpdateBrandDto {
    private UUID uuid;
    @UniqueBrandName
    private String name;

    public UpdateBrandDto() {
    }

    public UpdateBrandDto(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Brand name must not be null or empty!")
    @Size(min = 3, max = 20, message = "Brand name must be between 3 and 20 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "UpdateBrandDto{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
