package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.LabTest;
import edu.njit.cs684.electronichealthrecords.repository.LabRepository;
import edu.njit.cs684.electronichealthrecords.testusers.MockDoctorRole;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class LabTestServiceTest {

    @Autowired
    LabRepository labRepository;
    @Autowired
    PatientService patientService;

    @Test
    @MockDoctorRole
    public void saveInitialLabTest(){
        LabTest labTest = new LabTest();
        String labTestId = "125667734GFTLD90";
        labTest.setId(labTestId);
        labRepository.save(labTest);

    }

    @Test
    @MockDoctorRole
    public void findLabTestById(){
        LabTest labTest = new LabTest();
        String labTestId = "1234GFTLD90";
        labTest.setId(labTestId);
        labRepository.save(labTest);
        Optional<LabTest> optionalLabTest = labRepository.findById(labTestId);
    }
}
