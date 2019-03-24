package edu.njit.cs684.electronichealthrecords.services;


import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;

    public Staff createStaff(Staff staff) {
        Staff savedStaff = this.staffRepository.insert(staff);
        return savedStaff;
    }

    public List<Staff> getStaffInfo() {
        List<Staff> returnedStaff = this.staffRepository.findAll();

        return returnedStaff;


    }


}
