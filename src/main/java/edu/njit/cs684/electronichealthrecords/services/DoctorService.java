package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.LabTest;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Prescription;
import edu.njit.cs684.electronichealthrecords.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    LabTestService labTestService;

    @Secured({"ROLE_DOCTOR"})
    public void prescribeDrugs(String patientId, Prescription prescription) {
        Patient patient;
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        patient = optionalPatient.orElse(null);
        if (optionalPatient.isPresent()) {
            patient.addPrescription(prescription);
            patientRepository.save(patient);
        }
    }

    @Secured({"ROLE_DOCTOR"})
    public void prescribeTests(String patientId, String testName) {
        Patient patient;
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (optionalPatient.isPresent()) {
            patient = optionalPatient.orElse(null);
            LabTest labTest = new LabTest();
            labTest.setTestName(testName);
            LabTest savedLabTest = labTestService.saveInitialLabTest(labTest);
            patient.getLabTests().add(savedLabTest.getId());
            patientRepository.save(patient);
        }
    }

}
