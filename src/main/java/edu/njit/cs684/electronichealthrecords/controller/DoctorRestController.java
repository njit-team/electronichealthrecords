package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.LabTest;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.MedicalHistory;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Prescription;
import edu.njit.cs684.electronichealthrecords.services.DoctorService;
import edu.njit.cs684.electronichealthrecords.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorRestController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    @PostMapping(value = "/prescribe/drugs/{patientId}")
    public void prescribeDrugs(@PathVariable String patientId, @RequestBody @Validated Prescription prescription) {
        doctorService.prescribeDrugs(patientId, prescription);
    }

    @PostMapping(value = "/prescribe/tests/{patientId}")
    public void prescribeTests(@PathVariable String patientId, @RequestBody @Validated LabTest testName) {
        doctorService.prescribeTests(patientId, testName);
    }

    @GetMapping(value = "/medical/history/{patientId}")
    public MedicalHistory viewPatientMedicalHistory(@PathVariable @Validated String patientId) {
        MedicalHistory patientHistory = patientService.viewPatientMedicalHistory(patientId);
        return patientHistory;
    }

    @GetMapping(value = "/test/result/{patientId}")
    public List<LabTest> viewPatientTestResult(@PathVariable @Validated String patientId) {
        List<LabTest> patientTestResult = patientService.viewPatientTestResult(patientId);
        return patientTestResult;
    }

    @PostMapping(value = "/comments/{patientId}")
    public List<String> writeComments(@PathVariable String patientId, @RequestBody @Validated String comments) {
        doctorService.writeComments(patientId, comments);
        List<String> patientComments = patientService.viewCommentsOnPatient(patientId);
        return patientComments;
    }
}
