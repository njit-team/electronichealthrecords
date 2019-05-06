package edu.njit.cs684.electronichealthrecords;

import edu.njit.cs684.electronichealthrecords.domain.SampleData;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.AppUser;
import edu.njit.cs684.electronichealthrecords.repository.PatientRepository;
import edu.njit.cs684.electronichealthrecords.repository.StaffRepository;
import edu.njit.cs684.electronichealthrecords.services.SampleDataService;
import edu.njit.cs684.electronichealthrecords.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ElectronichealthrecordsApplication {

    @Autowired
    SampleDataService sampleDataService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ElectronichealthrecordsApplication.class, args);
    }

    @PostConstruct
    public void init() throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService = Executors.newWorkStealingPool();
        Iterable<SampleData> allSampleData = sampleDataService.getAllSampleData();
        List<SampleData> sampleDataList = new ArrayList<>();
        allSampleData.forEach(sampleDataList::add);
        for (int i = 0; i < sampleDataList.size(); i++) {
            SampleData sampleData = sampleDataList.get(i);
            if ((i < sampleDataList.size()/2) && !staffRepository.existsByAccountEmail(String.valueOf(sampleData.getEmail()))) {
                Staff staff = sampleDataService.convertSampleDataToStaff(sampleData);
                executorService.submit(() -> staffRepository.save(staff));
                AppUser appUser = sampleDataService.staffToUser(staff);
                executorService.submit(() -> userService.signUp(appUser));
            }

            if ((i >= sampleDataList.size()/2) && !patientRepository.existsByAccountEmail(String.valueOf(sampleData.getEmail()))) {
                Patient patient = sampleDataService.convertSampleDataToPatient(sampleData);
                patientRepository.save(patient);
                AppUser appUser = sampleDataService.patientToUser(patient);
                executorService.submit(() -> userService.signUp(appUser));
            }
        }
//        executorService.awaitTermination(3, TimeUnit.MINUTES);
//        executorService.shutdown();
    }
}
