package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import org.springframework.data.mongodb.core.index.Indexed;

public class Account {

    private Name name;
    private Address address;
    private String dateOfBirth;
    private Long phoneNumber;
    private String gender;
    @Indexed(unique = true)
    private String email;

    public Account() {
    }

    public Account(Name name, Address address, String dateOfBirth, Long phoneNumber, String gender, String email) {
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }




}
