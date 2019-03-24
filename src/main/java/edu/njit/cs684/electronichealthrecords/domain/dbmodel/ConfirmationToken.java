package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Document
public class ConfirmationToken {

    @Id
    private String tokenID;
    @NotNull(message = "confiramation token is missing")
    private String confirmationToken;
    @NotNull(message = "confiramation token create date is missing")
    private Date createDate;
    @NotNull(message = "Hospital object in confiramation token is missing")
    private Hospital hospital;


    public ConfirmationToken(Hospital hospital) {
        this.hospital = hospital;
        createDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreateDate() {
        return createDate;
    }
    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }



}

