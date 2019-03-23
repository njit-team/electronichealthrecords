package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Hospital;
import edu.njit.cs684.electronichealthrecords.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
