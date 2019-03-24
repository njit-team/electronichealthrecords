package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
public class Patient {

    @Id
    private String id;
    @Indexed(unique = true)
    @NotNull(message = "Patient account not sent in request.")
    private String patientId;
    @NotNull(message = "Patient account not sent in request.")
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", patientId='" + patientId + '\'' +
                ", account=" + account +
                '}';
    }
}




