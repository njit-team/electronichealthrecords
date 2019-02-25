package com.example.premier.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Patient {

    @Id
    private String id;
    private Account account;

    public Patient()  {



    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }













    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}




