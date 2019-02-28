package com.example.premier.web.restcontrollers;


import com.example.premier.service.EmailSenderService;
import com.example.premier.service.HospitalService;
import com.example.premier.repository.ConfirmationTokenRepository;
import com.example.premier.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.example.premier.model.Hospital;
import com.example.premier.model.ConfirmationToken;

import java.util.*;

@RestController

@RequestMapping(value ="/healthcare", method = RequestMethod.GET)
public class HospitalController {

    @Autowired
    HospitalService hospitalservice;
    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private HospitalRepository hospitalRepository;


    @PostMapping(value = "/register")
    public Hospital createHospital(@RequestBody Hospital hospital){
        Hospital existingHospital = this.hospitalRepository.findByEmailIgnoreCase(hospital.getEmail());
        if(existingHospital != null){
            System.out.println("User Already exists");
        }
         Hospital newHospital =  this.hospitalservice.createNewHospital(hospital);

            ConfirmationToken  confirmationToken = new ConfirmationToken(newHospital);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(hospital.getEmail());
            mailMessage.setSubject("Please Complete Your Registration");
            mailMessage.setFrom("jeromebabatunde@gmail.com");
            mailMessage.setText("please click here, to confirm your Registration" +"http://localhost:8081/confirm-account?token="
                                +confirmationToken.getConfirmationToken());

            emailSenderService.sendMail(mailMessage);
            System.out.print("Successful");



     return existingHospital;


    }
    @PostMapping(value = "/confirm-account")
    public ConfirmationToken confirmHospitalAccount(@RequestParam("token") String confirmToken){
        ConfirmationToken token = this.confirmationTokenRepository.findByConfirmationToken(confirmToken);
        System.out.println(token);
        if(token != null){
            Hospital newHospital = this.hospitalRepository.findByEmailIgnoreCase(token.getHospital().getEmail());
             System.out.println(newHospital);
            newHospital.setEnabled(true);
            hospitalRepository.save(newHospital);
            System.out.print("Account Verified");
        }else{
            System.out.println("link is broken or invalid");
        }
    return token;

    }



    @GetMapping(value = "/getHospitals")
    public List<Hospital> getHospitals(){
        List<Hospital> hospitals = this.hospitalservice.getHospitals();
        return hospitals;
    }


}
