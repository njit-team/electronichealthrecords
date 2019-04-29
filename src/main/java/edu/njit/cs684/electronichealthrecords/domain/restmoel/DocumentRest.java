package edu.njit.cs684.electronichealthrecords.domain.restmoel;

import org.springframework.web.multipart.MultipartFile;

public class DocumentRest {
    private MultipartFile document;
    private String patientId;

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
