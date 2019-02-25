package com.example.premier.web.restcontrollers;
import com.example.premier.model.Patient;
import com.example.premier.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/healthcare" , method = RequestMethod.GET )
public class AppController {

    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHelloAtIndexPage() {
        return "Hi";
    }

    @GetMapping(value = "/getPatient")
       public List<Patient> getPatient(){

        List<Patient> returnedPatient = this.patientService.getPatientInfo();

        return returnedPatient;

       }

       @PostMapping(value = "/createPatient")
        public Patient createStudent(@RequestBody Patient patient){
        Patient savedPatient = this.patientService.createPatient(patient);
        return savedPatient;
       }


        }