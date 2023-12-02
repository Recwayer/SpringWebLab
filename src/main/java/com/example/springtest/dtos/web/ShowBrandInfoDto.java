package com.example.springtest.dtos.web;

import java.util.UUID;

public class ShowBrandInfoDto {
    private UUID uuid;
    private String name;

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
}
