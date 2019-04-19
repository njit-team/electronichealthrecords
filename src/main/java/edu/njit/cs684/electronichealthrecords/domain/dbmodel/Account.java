package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    @Valid
    private Name name;

    @NotNull(message = "please enter your address")
    private Address address;
    @NotNull(message = "please enter your date of birth")
    private String dateOfBirth;
    @NotNull(message = "please enter your gender")
    private String gender;

    @Email
    @NotNull(message = "please enter your email")
    private String email;

    public Account() {
    }


    public Account(Name name, Address address, String dateOfBirth, Long phoneNumber, String gender, String Email) {
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
