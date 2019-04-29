package edu.njit.cs684.electronichealthrecords;

import edu.njit.cs684.electronichealthrecords.domain.SampleData;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.repository.PatientRepository;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import edu.njit.cs684.electronichealthrecords.services.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ElectronichealthrecordsApplication {

    @Autowired
    SampleDataService sampleDataService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    StaffRepository staffRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ElectronichealthrecordsApplication.class, args);
    }

    @PostConstruct
    public void init() {
        Iterable<SampleData> allSampleData = sampleDataService.getAllSampleData();
        List<SampleData> sampleDataList = new ArrayList<>();
        allSampleData.forEach(sampleDataList::add);
        for (int i = 0; i < sampleDataList.size(); i++) {
            SampleData sampleData = sampleDataList.get(i);
            if ((i < sampleDataList.size()/2) && !staffRepository.existsByAccountEmail(String.valueOf(sampleData.getEmail()))) {
                Staff staff = sampleDataService.convertSampleDataToStaff(sampleData);
                staffRepository.save(staff);
            }

            if ((i > sampleDataList.size()/2) && !patientRepository.existsByAccountEmail(String.valueOf(sampleData.getEmail()))) {
                Patient patient = sampleDataService.convertSampleDataToPatient(sampleData);
                patientRepository.save(patient);
            }
        }
    }
}
