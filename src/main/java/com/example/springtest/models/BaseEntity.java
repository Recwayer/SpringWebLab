package com.example.springtest.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    protected UUID uuid;
    protected Date created;
    protected Date modified;



    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
    @Id
    @UuidGenerator()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

}
