package com.example.springtest.models;

import com.example.springtest.models.enums.Engine;
import com.example.springtest.models.enums.Transmission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Engine engine;
    private String image_url;
    private long mileage;
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private Transmission transmission;
    private int year;
    @ManyToOne(fetch = FetchType.LAZY)
    private Model model;
    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;
    protected Offer() {
    }
}
