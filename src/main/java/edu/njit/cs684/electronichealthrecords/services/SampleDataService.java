package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.SampleData;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Account;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Address;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Name;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.AppUser;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.Role;
import edu.njit.cs684.electronichealthrecords.repository.SampleDataRepository;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    RestTemplate restTemplate;

    private static final String PATIENT_ROLE ="PATIENT";

    @Value("${users.test.password}")
    private String testPassword;

    Random random = new Random();
    List<String> staffTypes = List.of("DOCTOR", "LAB_TECHNICIAN", "RECEPTIONIST");

    public Iterable<SampleData> getAllSampleData() {
        Iterable<SampleData> all = sampleDataRepository.findAll();
        return all;
    }

    public List<Staff> saveStafftoMongoDb() {
        List<Staff> staffList = new ArrayList<>();

        Iterable<SampleData> allSampleData = this.getAllSampleData();
        for (SampleData sampleData : allSampleData) {
            Staff staff = convertSampleDataToStaff(sampleData);
            staffRepository.save(staff);
            staffList.add(staff);
        }
        return staffList;
    }

    public Staff convertSampleDataToStaff(SampleData sampleData) {
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
        account.setGender((random.nextInt(100) % 2 == 0) ? "M" : "F");
        account.setDateOfBirth(generateDob());
        account.setName(new Name(sampleData.getFirstName(), sampleData.getLastName()));
        account.setAddress(address);
        return account;
    }

    public Patient convertSampleDataToPatient(SampleData sampleData) {
        Account account = accountConverter(sampleData);
        Patient patient = new Patient();
        patient.setAccount(account);
        patient.setPatientId(String.valueOf(sampleData.getId()));
        patient.setId(String.valueOf(sampleData.getId()));
        return patient;
    }

    public String generateDob() {
        int year = random.nextInt(60) + 1;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(29) + 1;

        //2019-03-25T
        String dob = (2018 - year) + "-" + month + "-" + day + "T";
        return dob;
    }

    public AppUser staffToUser(Staff staff) {
        AppUser appUser = new AppUser();
        appUser.setId(staff.getStaffId());
        appUser.setAccount(staff.getAccount());
        appUser.setRoles(List.of(new Role(staff.getStaffType())));
        appUser.setUsername(staff.getAccount().getEmail());
        appUser.setUserType(staff.getStaffType());
        appUser.setPassword(testPassword);
        return appUser;
    }

    public AppUser patientToUser(Patient patient) {
        AppUser appUser = new AppUser();
        appUser.setId(patient.getPatientId());
        appUser.setAccount(patient.getAccount());
        appUser.setRoles(List.of(new Role(PATIENT_ROLE)));
        appUser.setUsername(patient.getAccount().getEmail());
        appUser.setUserType(PATIENT_ROLE);
        appUser.setPassword(testPassword);
        return appUser;
    }
}
