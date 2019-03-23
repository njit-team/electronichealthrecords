package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Document
public class Staff {

    @Id
    private String staffId;


    @Valid
    private Account account;

    @NotNull(message = "please enter your staff type")
    private String staffType;

    public Staff(){}

    public Staff(String staffId, String staffType, Account account) {
        this.staffId = staffId;
        this.staffType = staffType;
        this.account = account;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }



}
