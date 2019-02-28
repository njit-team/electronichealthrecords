package com.example.premier.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class ConfirmationToken {


    @Id
    private String tokenID;
    private String confirmationToken;
    private Date createDate;
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

