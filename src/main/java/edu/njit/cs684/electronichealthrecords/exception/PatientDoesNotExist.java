package edu.njit.cs684.electronichealthrecords.exception;

public class PatientDoesNotExist extends RuntimeException {
    public PatientDoesNotExist(String message) {
        super(message);
    }
}
