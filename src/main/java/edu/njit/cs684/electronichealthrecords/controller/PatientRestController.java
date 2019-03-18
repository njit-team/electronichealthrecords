package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientRestController {

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/get-patient")
    public List<Patient> getPatient(){

        List<Patient> returnedPatient = this.patientService.getPatientInfo();

        return returnedPatient;

    }

    @PostMapping(value = "/create-patient")
    public Patient createPatient(@RequestBody Patient patient) {
        Patient savedPatient = this.patientService.createPatient(patient);
        return savedPatient;
    }

    @GetMapping(value = "/find-patient/{patientId}")
    public Patient findPatientById(@PathVariable String patientId) {
        Patient patient = patientService.findPatientById(patientId);
        return patient;
    }

}