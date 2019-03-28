package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Prescription;
import edu.njit.cs684.electronichealthrecords.services.DoctorService;
import edu.njit.cs684.electronichealthrecords.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor")
public class DoctorRestController {

    @Autowired
    DoctorService doctorService;
    PatientService patientService;

    @PostMapping(value = "/prescribe/drugs")
    public void prescribeDrugs(@RequestBody String patientId, Prescription prescription) {
        doctorService.prescribeDrugs(patientId, prescription);
    }

    @PostMapping(value = "/prescribe/tests")
    public void prescribeTests(@RequestBody String patientId, String testName) {
        doctorService.prescribeTests(patientId, testName);
    }

    @GetMapping(value = "/medical/history/{patientId}")
    public Patient viewPatientMedicalHistory(@PathVariable @Validated String patientId) {
        Patient patient = patientService.viewPatientMedicalHistory(patientId);
        return patient;
    }
}
