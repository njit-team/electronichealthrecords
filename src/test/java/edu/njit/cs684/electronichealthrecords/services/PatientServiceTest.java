package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Account;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.repository.PatientRepository;
import edu.njit.cs684.electronichealthrecords.testusers.MockDoctorRole;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPatientInfo() {
    }

    @Test
    public void createPatient() {

    }

    @Test
    @MockDoctorRole
    public void findPatientByEmail() {
        Patient patient = new Patient();
        String expectedPatientId = "TON420";
        patient.setId(expectedPatientId);
        patient.getId();
        Account account = new Account();
        String expectedPatientemail = "123@njit.edu";
        account.setEmail(expectedPatientemail);
        patient.setAccount(account);

        if (patientRepository.existsById(expectedPatientId)) {
            patientRepository.deleteById(expectedPatientId);
        }

        patientService.createPatient(patient);
        Patient resultPatient = patientService.findPatientByEmail(expectedPatientemail);
        Assert.assertEquals("find patient by email method failed", expectedPatientId, resultPatient.getId());

    }

    @Test
    public void findPatientById() {
    }

    @Test
    @MockDoctorRole
    public void countPatient() {

        List<String> staffTypes = List.of("doctor", "lab_technician", "receptionist");

        Long patientCountResult = patientRepository.count();
        Assert.assertEquals("Patient count failed", Long.valueOf(1000), patientCountResult);
    }
}