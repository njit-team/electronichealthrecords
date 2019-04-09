package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("staff")
public class StaffRestController {

    @Autowired
    StaffService staffService;

    @PostMapping(value = "/create")
    public Staff createStaff(Staff staff) {
        Staff staff1 = staffService.createStaff(staff);
        return staff1;
    }

    @GetMapping(value = "view")
    public List<Staff> viewStaff() {
        List<Staff> staff = staffService.getStaffInfo();
        return staff;
    }

}
