package com.example.springtest.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean is_active;

    private UserRole role;
    private String image_url;

    private List<Offer> offers;

    protected User() {
    }

    public User(String username, String password, String firstName, String lastName, boolean is_active, UserRole role, String image_url, List<Offer> offers) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.is_active = is_active;
        this.role = role;
        this.image_url = image_url;
        this.offers = offers;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public boolean is_active() {
        return this.is_active;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public UserRole getRole() {
        return this.role;
    }

    public String getImage_url() {
        return this.image_url;
    }

    @OneToMany(mappedBy = "seller",cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    public List<Offer> getOffers() {
        return this.offers;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void set_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
