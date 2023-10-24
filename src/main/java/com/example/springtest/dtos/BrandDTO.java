package com.example.springtest.dtos;

import java.util.Date;
import java.util.UUID;

public class BrandDTO {
    private UUID uuid;
    private Date created;
    private Date modified;
    private String name;

    public BrandDTO() {
    }

    public BrandDTO(UUID uuid, Date created, Date modified, String name) {
        this.uuid = uuid;
        this.created = created;
        this.modified = modified;
        this.name = name;
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


}