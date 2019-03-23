package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Secured("ROLE_DOCTOR")
    public List<Patient> getPatientInfo(){
        List<Patient> returnedPatient = this.patientRepository.findAll();
        return returnedPatient;
    }

    @Secured("ROLE_DOCTOR")
    public Patient createPatient(Patient patient){
        Patient savedPatient = this.patientRepository.insert(patient);
        return savedPatient;
    }

    @Secured("ROLE_DOCTOR")
    public Patient findPatientByEmail(String email) {
        Patient patient;
        Optional<Patient> optionalPatient = patientRepository.findByAccountEmail(email);
        patient = optionalPatient.orElse(null);
        return patient;

    }

    @Secured("ROLE_DOCTOR")
    public Patient findPatientById(String patientId) {
        Patient patient;
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        patient = optionalPatient.orElse(null);
        return patient;

    }


}
