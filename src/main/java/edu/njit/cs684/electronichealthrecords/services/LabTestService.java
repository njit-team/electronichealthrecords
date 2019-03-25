package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.LabTest;
import edu.njit.cs684.electronichealthrecords.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabTestService {

    @Autowired
    LabRepository labRepository;
    @Autowired
    PatientService patientService;

    @Secured("ROLE_LABTECHNICIAN")
    public LabTest performLabTest(String labTestId, String testResult, String comments) {
        LabTest savedLabTest = null;
        Optional<LabTest> labTestbyId = labRepository.findById(labTestId);
        if (labTestbyId.isPresent()) {
            LabTest labTest = labTestbyId.get();
            labTest.setTestResult(testResult);
            labTest.setComments(comments);
            savedLabTest = labRepository.save(labTest);
        }
        return savedLabTest;
    }

    /**
     * Save the LabTest initial request without test result and assign id to test result.
     *
     * @param labTest
     * @return
     */
    @Secured({"ROLE_LABTECHNICIAN", "ROLE_DOCTOR"})
    public LabTest saveInitialLabTest(LabTest labTest) {
        LabTest savedLabTest = labRepository.save(labTest);
        return savedLabTest;
    }

    @Secured("ROLE_LABTECHNICIAN")
    public LabTest findLabTestById(String labTestId) {
        LabTest labTest;
        Optional<LabTest> optionalLabTest = labRepository.findById(labTestId);
        labTest = optionalLabTest.orElse(null);
        return labTest;
    }


    @Secured("ROLE_LABTECHNICIAN")
    public List<LabTest> findAllLabTests() {
        List<LabTest> all = labRepository.findAll();
        return all;
    }

}
