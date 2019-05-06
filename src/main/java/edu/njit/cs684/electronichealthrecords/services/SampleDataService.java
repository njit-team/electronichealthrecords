package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.SampleData;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Account;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Address;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.MedicalHistory;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Name;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Prescription;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.AppUser;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.Role;
import edu.njit.cs684.electronichealthrecords.repository.SampleDataRepository;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.lang.model.element.NestingKind;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    Map<String, Role> roleMap = new HashMap<>();

    {
        roleMap.put(staffTypes.get(0), new Role("ROLE_" + staffTypes.get(0)));
        roleMap.put(staffTypes.get(1), new Role("ROLE_" + staffTypes.get(1)));
        roleMap.put(staffTypes.get(2), new Role("ROLE_" + staffTypes.get(2)));
    }

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

        patient.addcomments(generateRandomTextLine(6));
        patient.setLabTests(List.of(generateRandomTextLine(3), generateRandomTextLine(2)));
        Prescription prescription = getRandomPrescription();
        patient.setPrescription(List.of(prescription));
        MedicalHistory medicalHistory = getRandomMedicalHistory();
        patient.setMedicalHistory(medicalHistory);

        return patient;
    }

    private Prescription getRandomPrescription() {
        Prescription prescription = new Prescription();
        prescription.setAdditionalComments(generateRandomTextLine(8));
        prescription.setDosage(generateRandomTextLine(4));
        prescription.setMedicineName(generateRandomTextLine(4));
        prescription.setActive((generateRandomTextLine(3).length() % 2 == 0) ? true : false);
        return prescription;
    }

    private MedicalHistory getRandomMedicalHistory() {
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setAdditionalComments(generateRandomTextLine(5));
        medicalHistory.setRecord(generateRandomTextLine(7));
        return medicalHistory;
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
        if(staff.getStaffType().equalsIgnoreCase(roleMap.get("DOCTOR").getRole())){
            List<Role> roles = List.copyOf(roleMap.values());
            appUser.setRoles(roles);
        }else {
            appUser.setRoles(List.of(roleMap.get(staff.getStaffType())));
        }
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

    public SampleData getRandomStaffSampleData(){
        Iterable<SampleData> all = sampleDataRepository.findAll();
        List<SampleData> sampleData = new ArrayList<>();
        all.forEach(sampleData::add);
        return sampleData.get(random.nextInt(1000));
    }

    public SampleData getRandomPatientSampleData(){
        Iterable<SampleData> all = sampleDataRepository.findAll();
        List<SampleData> sampleData = new ArrayList<>();
        all.forEach(sampleData::add);
        return sampleData.get(random.nextInt(1000) + 1000);
    }

    public String generateRandomTextLine(int wordCount){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i <= wordCount; i++) {
            String randomWord = RandomStringUtils.randomAlphabetic(random.nextInt(5) + 2);
            stringBuilder.append(randomWord);
            stringBuilder.append(' ');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append('.');
        return stringBuilder.toString();
    }
}
