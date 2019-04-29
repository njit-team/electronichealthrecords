package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.SampleData;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Account;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Address;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Name;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.repository.SampleDataRepository;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class SampleDataService {

    @Autowired
    SampleDataRepository sampleDataRepository;
    @Autowired
    StaffRepository staffRepository;

    Random random = new Random();
    List<String> staffTypes = List.of("doctor", "lab_technician", "receptionist");

    public Iterable<SampleData> getAllSampleData(){
        Iterable<SampleData> all = sampleDataRepository.findAll();
        return all;
    }

    public List<Staff> saveStafftoMongoDb(){
        List<Staff> staffList = new ArrayList<>();

        Iterable<SampleData> allSampleData = this.getAllSampleData();
        for (SampleData sampleData : allSampleData) {
            Staff staff = convertSampleDataToStaff(sampleData);
            staffRepository.save(staff);
            staffList.add(staff);
        }
        return staffList;
    }

    public Staff convertSampleDataToStaff(SampleData sampleData){
        Account account = accountConverter(sampleData);

        Staff staff = new Staff();
        staff.setStaffType(staffTypes.get(random.nextInt(3)));
        staff.setAccount(account);
        staff.setStaffId(String.valueOf(sampleData.getId()));
        return staff;
    }

    private Account accountConverter(SampleData sampleData) {
        Address address = new Address();
        address.setStreet(sampleData.getStreet());
        address.setCity(sampleData.getCity());
        address.setState(sampleData.getState());
        address.setCountry(sampleData.getCountry());
        address.setZipCode(sampleData.getZipCode());
        address.setPhoneNumber(sampleData.getPhoneNumber());

        Account account = new Account();
        account.setEmail(sampleData.getEmail());
        account.setGender((random.nextInt(100) % 2 == 0) ? "M" : "F" );
        account.setDateOfBirth(generateDob());
        account.setName(new Name(sampleData.getFirstName(), sampleData.getLastName()));
        account.setAddress(address);
        return account;
    }

    public Patient convertSampleDataToPatient(SampleData sampleData){
        Account account = accountConverter(sampleData);
        Patient patient = new Patient();
        patient.setAccount(account);
        patient.setPatientId(String.valueOf(sampleData.getId()));
        patient.setId(String.valueOf(sampleData.getId()));
        return patient;
    }

    public String generateDob(){
        int year = random.nextInt(60)+1;
        int month = random.nextInt(12)+1;
        int day = random.nextInt(29)+1;

        //2019-03-25T
        String dob = (2018 - year) + "-" + month + "-" + day + "T";
        return dob;
    }

}
