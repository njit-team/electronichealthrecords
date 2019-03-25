package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientRestController {

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/get/all")
    public List<Patient> getPatient(){
        List<Patient> returnedPatient = this.patientService.getPatientInfo();
        return returnedPatient;

    }

    @PostMapping(value = "/create")
    public Patient createPatient(@RequestBody @Validated Patient patient) {
        Patient savedPatient = this.patientService.createPatient(patient);
        return savedPatient;
    }

    @GetMapping(value = "/find/{patientId}")
    public Patient findPatientById(@PathVariable @Validated String patientId) {
        Patient patient = patientService.findPatientById(patientId);
        return patient;
    }

    @GetMapping(value = "/medical/history/{patientId}")
    public Patient viewPatientMedicalHistory(@RequestBody @Validated String patientId) {
        Patient patient = patientService.viewPatientMedicalHistory(patientId);
        return patient;
    }

    @GetMapping(value = "/test/result/{patientId}")
    public Patient viewPatientTestResult(@RequestBody @Validated String patientId) {
        Patient patient = patientService.viewPatientTestResult(patientId);
        return patient;
    }

    @GetMapping(value = "/prescription/{patientId}")
    public Patient viewPatientPrescription(@RequestBody @Validated String patientId) {
        Patient patient = patientService.viewPatientPrescription(patientId);
        return patient;
    }

    @GetMapping(value = "/comments/{patientId}")
    public Patient viewCommentsOnPatient(@RequestBody @Validated String patientId) {
        Patient patient = patientService.viewCommentsOnPatient(patientId);
        return patient;
    }

}