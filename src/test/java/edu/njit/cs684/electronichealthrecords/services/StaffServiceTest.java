package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import edu.njit.cs684.electronichealthrecords.testusers.MockDoctorRole;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;

    @Test
    @MockDoctorRole
    public void countStaff() {

        List<String> staffTypes = List.of("doctor", "lab_technician", "receptionist");

        Long doctorCountExpected = staffRepository.countByStaffType(staffTypes.get(0));
        Long labTechnicianCountExpected = staffRepository.countByStaffType(staffTypes.get(1));
        Long receptionistCountExpected = staffRepository.countByStaffType(staffTypes.get(2));
        long totalCount = doctorCountExpected + labTechnicianCountExpected + receptionistCountExpected;
        Assert.assertEquals("count staff method failed", 1000, totalCount);
    }

}
