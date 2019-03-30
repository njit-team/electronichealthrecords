package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hospital {

    @Id
    private String hospitalID;
    @NotNull(message = "Hospital name is not sent in request")
    private String name;
    @NotNull(message = "Hospital address is not sent in request")
    private String address;
    @NotNull(message = "Hospital phoneNumber is not sent in request")
    private String phoneNumber;
    @NotNull(message = "Hospital email is not sent in request")
    private String email;
    @NotNull(message = "Hospital active status is not sent in request")
    private boolean isEnabled;


    public Hospital(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getHospitalID() {
        return hospitalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

}
