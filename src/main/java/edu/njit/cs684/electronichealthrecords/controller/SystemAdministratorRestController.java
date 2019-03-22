package edu.njit.cs684.electronichealthrecords.controller;


import edu.njit.cs684.electronichealthrecords.controller.response.ErrorResponse;

import edu.njit.cs684.electronichealthrecords.controller.response.Violation;
import edu.njit.cs684.electronichealthrecords.domain.Staff;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import edu.njit.cs684.electronichealthrecords.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;



@RestController
@RequestMapping(value = "/healthcare/staff", method = RequestMethod.GET)
public class SystemAdministratorRestController {
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    StaffService staffService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ErrorResponse error = new ErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }



    @PostMapping(value="/registerStaff")
    public @ResponseBody Staff createStaff(@RequestBody @Valid Staff staff) throws Exception{
        try {
            Staff savedStaff = this.staffService.createStaff(staff);
            return savedStaff;
        }catch(Exception e){
            throw e;
        }

    }


    @GetMapping(value = "/getStaff")
    public List<Staff> getPatient(){

        List<Staff> returnedPatient = this.staffService.getStaffInfo();

        return returnedPatient;

    }


}
