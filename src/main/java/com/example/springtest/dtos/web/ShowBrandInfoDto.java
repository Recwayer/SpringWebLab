package com.example.springtest.dtos.web;


import java.io.Serializable;
import java.util.UUID;
public class ShowBrandInfoDto implements Serializable {
    private UUID uuid;
    private String name;

    public ShowBrandInfoDto() {
    }

    public ShowBrandInfoDto(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

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
        return "ShowBrandInfoDto{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
