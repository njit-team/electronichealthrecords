package edu.njit.cs684.electronichealthrecords.domain;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Account {
    @Valid
    private Name name;
    @NotNull(message = "please enter your address")
    private String address;
    @NotNull(message="please enter your date of birth")
    private String dateOfBirth;
    @NotNull(message = "please enter your phone number ")
    private Long phoneNumber;
    @NotNull(message = "please enter your gender")
    private String gender;


    @Email
    @NotNull(message = "please enter your email")
    private String email;

    public Account(Name name, String address, String dateOfBirth, Long phoneNumber, String gender,String Email) {
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;

    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }




}
