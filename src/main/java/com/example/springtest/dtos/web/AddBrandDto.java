package com.example.springtest.dtos.web;

import com.example.springtest.utils.validation.annotations.UniqueBrandName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddBrandDto {
    @UniqueBrandName
    private String name;

    public AddBrandDto() {
    }

    public AddBrandDto(String name) {
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
}
