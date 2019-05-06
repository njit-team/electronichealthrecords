package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.SampleData;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import edu.njit.cs684.electronichealthrecords.testusers.MockDoctorRole;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    SampleDataService sampleDataService;

    @Test
    @MockDoctorRole
    public void countStaff() {

        List<String> staffTypes = List.of("DOCTOR", "LAB_TECHNICIAN", "RECEPTIONIST");

        Long doctorCountExpected = staffRepository.countByStaffType(staffTypes.get(0));
        Long labTechnicianCountExpected = staffRepository.countByStaffType(staffTypes.get(1));
        Long receptionistCountExpected = staffRepository.countByStaffType(staffTypes.get(2));
        long totalCount = doctorCountExpected + labTechnicianCountExpected + receptionistCountExpected;
        Assert.assertEquals("count staff method failed", 1000, totalCount);
    }

    @Test
    public void getStaffInfo() {
        SampleData randomSampleData = sampleDataService.getRandomStaffSampleData();
        Staff byAccountEmail = staffRepository.findByAccountEmail(randomSampleData.getEmail());
        Assert.assertNotNull(byAccountEmail);
    }
}
