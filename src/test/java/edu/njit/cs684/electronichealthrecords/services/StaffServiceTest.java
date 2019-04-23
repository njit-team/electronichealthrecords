package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Account;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Name;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import edu.njit.cs684.electronichealthrecords.testusers.MockDoctorRole;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedList;
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

        String staffType = "receptionist";
        String staffId1 = "REC1025";
        String staffId2 = "REC1050";

        if (staffRepository.existsById(staffId1)) {
            staffRepository.deleteById(staffId1);
        }
        if (staffRepository.existsById(staffId2)) {
            staffRepository.deleteById(staffId2);
        }
//        if (staffRepository.existsById(staff2.getStaffId())) {
//            staffRepository.deleteById(staff2.getStaffId());
//        }
//        if (staffRepository.existsById(staff3.getStaffId())) {
//            staffRepository.deleteById(staff3.getStaffId());
//        }

        Staff staff0 = new Staff(staffId1,staffType);
        Staff staff1 = new Staff(staffId2,staffType);
//        Staff staff2 = new Staff("rec13",staffType);
//        Staff staff3 = new Staff("rec14",staffType);



        Account staffAccount0 = new Account();
        staffAccount0.setEmail("receptn25@xyz");
        staff0.setAccount(staffAccount0);

        Account staffAccount1 = new Account();
        staffAccount1.setEmail("receptn50@lmn");
        staff1.setAccount(staffAccount1);
//
//        Account staffAccount2 = new Account();
//        staffAccount2.setEmail("23@com");
//        staff2.setAccount(staffAccount0);
//
//        Account staffAccount3 = new Account();
//        staffAccount3.setEmail("24@com");
//        staff3.setAccount(staffAccount0);



        staffRepository.save(staff0);
        staffRepository.save(staff1);
//        staffRepository.save(staff2);
//        staffRepository.save(staff3);



        Long countExpected = staffRepository.countByStaffType(staffType);
                Long countResult = staffService.countStaff(staffType);
        Assert.assertEquals("count staff method failed", countExpected,countResult);

        if (staffRepository.existsById(staffId1)) {
            staffRepository.deleteById(staffId1);
        }
        if (staffRepository.existsById(staffId2)) {
            staffRepository.deleteById(staffId2);
        }
//        if (staffRepository.existsById(staff2.getStaffId())) {
//            staffRepository.deleteById(staff2.getStaffId());
//        }
//        if (staffRepository.existsById(staff3.getStaffId())) {
//            staffRepository.deleteById(staff3.getStaffId());
//        }


    }

}
