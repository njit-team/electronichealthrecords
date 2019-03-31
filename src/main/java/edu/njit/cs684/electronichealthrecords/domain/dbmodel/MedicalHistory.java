package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import java.time.ZonedDateTime;

public class MedicalHistory {

    private String timeStamp = ZonedDateTime.now().toString();
    private String record;
    private String additionalComments;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStampString) {
        this.timeStamp = timeStampString;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "timeStamp=" + timeStamp +
                ", record='" + record + '\'' +
                ", additionalComments='" + additionalComments + '\'' +
                '}';
    }
}
