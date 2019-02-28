package com.example.premier.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class ConfirmationToken {
    @Id
    private Long tokenID;
    private String confirmationToken;
    private Date createDate;
    private Hospital hospital;


    public ConfirmationToken(Hospital hospital) {
        this.hospital = hospital;
        createDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public Long getTokenID() {
        return tokenID;
    }

    public void setTokenID(Long tokenID) {
        this.tokenID = tokenID;
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

