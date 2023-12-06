package com.example.springtest.dtos.web;

import java.util.List;
import java.util.UUID;

public class ShowDetailedBrandInfoDto {
    private UUID uuid;
    private String name;

    private List<ShowModelInfoDto> models;

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

    public List<ShowModelInfoDto> getModels() {
        return models;
    }

    public void setModels(List<ShowModelInfoDto> models) {
        this.models = models;
    }
}
