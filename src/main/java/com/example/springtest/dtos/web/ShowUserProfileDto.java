package com.example.springtest.dtos.web;

import java.util.List;

public class ShowUserProfileDto {
    private String username;
    private String firstName;
    private String lastName;
    private List<ShowOfferInfoDto> offers;

    public ShowUserProfileDto() {
    }

    public ShowUserProfileDto(String username, String firstName, String lastName, List<ShowOfferInfoDto> offers) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.offers = offers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ShowOfferInfoDto> getOffers() {
        return offers;
    }

    public void setOffers(List<ShowOfferInfoDto> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "ShowUserProfileDto{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", offers=" + offers +
                '}';
    }
}
