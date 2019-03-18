package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/healthcare" , method = RequestMethod.GET )
public class PatientRestController {

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/getPatient")
    public List<Patient> getPatient(){

        List<Patient> returnedPatient = this.patientService.getPatientInfo();

        return returnedPatient;

    }

    @PostMapping(value = "/createPatient")
    public Patient createPatient(@RequestBody Patient patient) {
        Patient savedPatient = this.patientService.createPatient(patient);
        return savedPatient;
    }

    @GetMapping
    public Patient findPatientById(String patientId) {
        Patient patient = patientService.findPatientById(patientId);
        return patient;
    }

}