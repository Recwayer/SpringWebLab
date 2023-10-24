package com.example.springtest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean is_active;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserRole role;
    private String image_url;
    @OneToMany(mappedBy = "seller",cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Offer> offers;

    protected User() {
    }
}
