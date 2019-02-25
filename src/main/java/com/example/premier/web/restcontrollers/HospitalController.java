package com.example.premier.web.restcontrollers;


import com.example.premier.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.example.premier.model.Hospital;

import java.util.*;

@RestController

@RequestMapping(value ="/healthcare", method = RequestMethod.GET)
public class HospitalController {

    @Autowired
    HospitalService hospitalservice;

    @PostMapping(value = "/postHospitals")
    public Hospital createHospital(@RequestBody Hospital hospital){
        Hospital newHospital = this.hospitalservice.createNewHospital(hospital);

        return newHospital;



    }

    @GetMapping(value = "/getHospitals")
    public List<Hospital> getHospitals(){
        List<Hospital> hospitals = this.hospitalservice.getHospitals();
        return hospitals;
    }


}
