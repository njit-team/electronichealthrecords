package edu.njit.cs684.electronichealthrecords.controller.response;


import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private List<Violation> violations = new ArrayList<>();

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }
}





