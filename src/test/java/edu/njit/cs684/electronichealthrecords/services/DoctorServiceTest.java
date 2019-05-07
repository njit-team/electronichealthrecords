package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.SampleData;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Prescription;
import edu.njit.cs684.electronichealthrecords.repository.PatientRepository;
import edu.njit.cs684.electronichealthrecords.testusers.MockDoctorRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    SampleDataService sampleDataService;

    @Test
    @MockDoctorRole
    public void prescribeDrugs() {
        Patient patient = new Patient();
        Prescription prescription = new Prescription();

        String expectedPatientId = "PNT45";
        String expectedMedicineName = "Metformin";
        String expectedDosage = "twice daily";
        String expectedAdditonalComments = "normal";

        prescription.setMedicineName(expectedMedicineName);
        prescription.setDosage(expectedDosage);
        prescription.setAdditionalComments(expectedAdditonalComments);

        patient.setPatientId(expectedPatientId);

        doctorService.prescribeDrugs(expectedPatientId, prescription);

        Assert.assertEquals("drug prescription failed", expectedPatientId, patient.getPatientId());
        Assert.assertEquals("medicine name drug prescription method failed", expectedMedicineName, prescription.getMedicineName());
        Assert.assertEquals("dosage of drug prescription method failed", expectedDosage, prescription.getDosage());
        Assert.assertEquals("comments on drug prescription method failed", expectedAdditonalComments, prescription.getAdditionalComments());



    }

    @Test
    @MockDoctorRole
    public void prescribeTests(){
        SampleData sampleData = sampleDataService.getRandomPatientSampleData();
        Patient patient = patientService.findPatientById(String.valueOf(sampleData.getId()));
//        patient.setLabTests();

    }

    @Test
    @MockDoctorRole
    public void writeComments(){
        SampleData sampleData = sampleDataService.getRandomPatientSampleData();
        Patient patient = patientService.findPatientById(String.valueOf(sampleData.getId()));
        patient.addcomments("");
    }

}
