package com.example.springtest.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    private String name;

    private List<Model> models;

    protected Brand() {
    }

    public Brand(String name, List<Model> models) {
        this.name = name;
        this.models = models;
    }
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    public List<Model> getModels() {
        return this.models;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", models=" + models +
                '}';
    }
}
