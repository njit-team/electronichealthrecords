package edu.njit.cs684.electronichealthrecords.services;


import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Secured({"ROLE_ANONYMOUS"})
    public Staff createStaff(Staff staff) {
        int length = 5;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";
        String str = new Random().ints(length, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
        staff.setStaffId(str);

        Staff savedStaff = this.staffRepository.insert(staff);
        return savedStaff;
    }

    @Secured({"ROLE_ANONYMOUS"})
    public List<Staff> getStaffInfo() {
        List<Staff> returnedStaff = this.staffRepository.findAll();
        return returnedStaff;
    }


}
