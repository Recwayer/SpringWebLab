package com.example.springtest.dtos.web;

import com.example.springtest.models.enums.Category;
import com.example.springtest.utils.validation.annotations.UniqueModelName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class AddModelDto {
    @UniqueModelName
    private String name;
    private Category category;
    private String brandName;
    private int startYear;
    private int endYear;


    public AddModelDto() {
    }

    public AddModelDto(String name, Category category, String brandName, int startYear, int endYear) {
        this.name = name;
        this.category = category;
        this.brandName = brandName;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    @NotEmpty(message = "Model name must not be null or empty!")
    @Size(min = 3, max = 20, message = "Model name must be between 3 and 20 characters!")
    public String getName() {
        return name;
    }

    @NotNull(message = "Category cannot be null or empty!")
    public Category getCategory() {
        return category;
    }

    @NotEmpty(message = "Please choose a Brand!")
    public String getBrandName() {
        return brandName;
    }

    @NotNull(message = "StartYear cannot be null or empty!")
    @Min(value = 1, message = "StartYear must be a positive number!")
    public int getStartYear() {
        return startYear;
    }

    @NotNull(message = "EndYear cannot be null or empty!")
    @Min(value = 1, message = "EndYear must be a positive number!")
    public int getEndYear() {
        return endYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }


    @Override
    public String toString() {
        return "AddModelDto{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", brandName='" + brandName + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                '}';
    }
}
