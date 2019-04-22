package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.LabTest;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Prescription;
import edu.njit.cs684.electronichealthrecords.exception.PatientDoesNotExist;
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
        Optional<Patient> optionalPatient = patientRepository.findByPatientId(patientId);
        patient = optionalPatient.orElse(null);
        if (optionalPatient.isPresent()) {
            patient.addPrescription(prescription);
            patientRepository.save(patient);
        }
    }

    @Secured({"ROLE_DOCTOR"})
    public void prescribeTests(String patientId, LabTest labTest) {
        Patient patient;
        Optional<Patient> optionalPatient = patientRepository.findByPatientId(patientId);

        if (optionalPatient.isPresent()) {
            patient = optionalPatient.orElse(null);
            LabTest savedLabTest = labTestService.saveInitialLabTest(labTest);
            patient.getLabTests().add(savedLabTest.getId());
            patientRepository.save(patient);
        } else {
            throw new PatientDoesNotExist("Patient with id " + patientId + " does not exist.");
        }
    }

    @Secured({"ROLE_DOCTOR"})
    public void writeComments(String patientId, String comments) {
        Patient patient;
        Optional<Patient> optionalPatient = patientRepository.findByPatientId(patientId);
        patient = optionalPatient.orElse(null);
        if (optionalPatient.isPresent()) {
            patient.addcomments(comments);
            patientRepository.save(patient);
        }

    }


}
