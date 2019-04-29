package edu.njit.cs684.electronichealthrecords.services;


import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Secured({"ROLE_ANONYMOUS"})
    public Staff createStaff(Staff staff) {

        Staff savedStaff = this.staffRepository.insert(staff);
        return savedStaff;
    }

    @Secured({"ROLE_ANONYMOUS"})
    public List<Staff> getStaffInfo() {
        List<Staff> returnedStaff = this.staffRepository.findAll();
        return returnedStaff;
    }

    @Secured({"ROLE_DOCTOR", "ROLE_RECEPTIONIST", "ROLE_PATIENT", "ROLE_ANONYMOUS"})
    public long countStaff(String staffType){
        long numberOfUser = staffRepository.countByStaffType(staffType);
        return numberOfUser;
    }

}
