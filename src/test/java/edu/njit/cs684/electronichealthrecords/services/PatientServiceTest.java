package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.SampleData;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Account;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.repository.PatientRepository;
import edu.njit.cs684.electronichealthrecords.testusers.MockDoctorRole;
import edu.njit.cs684.electronichealthrecords.testusers.MockPatientRole;
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

    @Autowired
    SampleDataService sampleDataService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getPatientInfo() {
        Patient patientById = patientService.findPatientById("1001");
        Assert.assertNotNull(patientById);
    }

    @Test
    public void createPatient() {

    }

    @Test
    @MockDoctorRole
    public void findPatientByEmail() {
        SampleData sampleData = sampleDataService.getRandomPatientSampleData();
        Patient resultPatient = patientService.findPatientByEmail(sampleData.getEmail());
        Assert.assertNotNull("find patient by email method failed", resultPatient.getId());

    }

    @Test
    @MockDoctorRole
    public void findPatientById() {
        SampleData sampleData = sampleDataService.getRandomPatientSampleData();
        Patient patient = patientService.findPatientById(String.valueOf(sampleData.getId()));
        Assert.assertNotNull("find patient by id method failed", patient);
        Assert.assertEquals("find patient method failed", sampleData.getEmail(), patient.getAccount().getEmail());

    }

    @Test
    @MockDoctorRole
    public void countPatient() {
        List<String> staffTypes = List.of("doctor", "lab_technician", "receptionist");
        Long patientCountResult = patientRepository.count();
        Assert.assertEquals("Patient count failed", Long.valueOf(1000), patientCountResult);
    }
}