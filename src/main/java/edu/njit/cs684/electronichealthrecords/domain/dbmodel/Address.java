package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import javax.validation.constraints.NotNull;

public class Address {

    @NotNull(message = "Street address is not sent in request")
    private String street;
    private String apartment;
    @NotNull(message = "city in address is not sent in request")
    private String city;
    @NotNull(message = "state in address is not sent in request")
    private String state;
    @NotNull(message = "zipcode in address is not sent in request")
    private String zipCode;
    @NotNull(message = "phoneNumber in address is not sent in request")
    private String phoneNumber;
    @NotNull(message = "Street address is not sent in request")
    private String country;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", apartment='" + apartment + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
