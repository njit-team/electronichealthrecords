package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.MedicalHistory;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Prescription;
import edu.njit.cs684.electronichealthrecords.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    public MedicalHistory viewPatientMedicalHistory(@PathVariable @Validated String patientId) {
        MedicalHistory patientMedicalHistory = patientService.viewPatientMedicalHistory(patientId);
        return patientMedicalHistory;
    }

    @GetMapping(value = "/test/result/{patientId}")
    public List<String> viewPatientTestResult(@PathVariable @Validated String patientId) {
        List<String> patientTestResult = patientService.viewPatientTestResult(patientId);
        return patientTestResult;
    }

    @GetMapping(value = "/prescription/{patientId}")
    public List<Prescription> viewPatientPrescription(@PathVariable @Validated String patientId) {
        List<Prescription> patientPrescription = patientService.viewPatientPrescription(patientId);
        return patientPrescription;
    }

    @GetMapping(value = "/comments/{patientId}")
    public List<String> viewCommentsOnPatient(@PathVariable @Validated String patientId) {
        List<String> patientComments = patientService.viewCommentsOnPatient(patientId);
        return patientComments;
    }

    @GetMapping(value = "/count")
    public long countPatient(){
        long numberOfPatients = patientService.countPatient();
        return numberOfPatients;
    }
}