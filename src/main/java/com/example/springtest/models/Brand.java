package com.example.springtest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Model> models;
    protected Brand() {
    }
}
