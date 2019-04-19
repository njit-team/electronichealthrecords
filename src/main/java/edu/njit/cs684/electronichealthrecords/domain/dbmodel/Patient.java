package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {

    @Id
    private String id;
    @Indexed(unique = true)
    private String patientId;
    @NotNull(message = "Patient account not sent in request.")
    private Account account;
    private MedicalHistory medicalHistory = new MedicalHistory();
    private List<String> labTests = new ArrayList<>();
    private List<Prescription> prescription = new ArrayList<>();
    private List<String> additionalComments = new ArrayList<>();


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

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<String> getLabTests() {
        return labTests;
    }

    public void setLabTests(List<String> labTests) {
        this.labTests = labTests;
    }

    public List<Prescription> getPrescription() {
        return prescription;
    }

    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }

    public List<String> getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(List<String> additionalComments) {
        this.additionalComments = additionalComments;
    }

    public void addcomments(String comment) {
        additionalComments.add(comment);
    }

    public void addPrescription(Prescription prescription1) {
        prescription.add(prescription1);
    }
}




