package com.example.springtest.models;

import com.example.springtest.models.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    private String image_url;
    private int startYear;
    private int endYear;
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;
    @OneToMany(mappedBy = "model",cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Offer> offers;

    protected Model() {
    }
}
