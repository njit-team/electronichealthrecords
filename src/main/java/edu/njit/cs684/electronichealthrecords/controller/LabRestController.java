package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.LabTest;
import edu.njit.cs684.electronichealthrecords.services.LabTestService;
import edu.njit.cs684.electronichealthrecords.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("labtest")
public class LabRestController {
    @Autowired
    LabTestService labTestService;
    @Autowired
    PatientService patientService;

    @GetMapping(value = "/perform")
    public LabTest performLabTest(@RequestParam("labTestId") String labTestId,
                                  @RequestParam("testResult") String testResult,
                                  @RequestParam("comments") String comments) {
        LabTest labTest = labTestService.performLabTest(labTestId, testResult, comments);
        return labTest;
    }

    @GetMapping(value = "/findById")
    public LabTest findLabTestById(String labTestId) {
        LabTest labTest = labTestService.findLabTestById(labTestId);
        return labTest;
    }

    @GetMapping(value = "/findAllLabTests")
    public List<LabTest> findAllLabTests() {
        List<LabTest> all = labTestService.findAllLabTests();
        return all;
    }
}
