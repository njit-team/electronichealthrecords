package com.example.premier.service;


import com.example.premier.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.premier.model.Hospital;

import java.util.*;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;
    public Hospital createNewHospital(Hospital hospital){

        Hospital newHospital = this.hospitalRepository.insert(hospital);

        return newHospital;


    }

    public List<Hospital> getHospitals(){
        List<Hospital> hospitals = this.hospitalRepository.findAll();

        return hospitals;
    }
}
