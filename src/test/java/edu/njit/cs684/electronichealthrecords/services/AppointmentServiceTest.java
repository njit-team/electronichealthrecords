package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.Appointment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.ZonedDateTime;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void bookAppointment() {
        Assert.assertNotNull(appointmentService);
        String patientId = "SD12";
        String doctorId = "DOC21";
        ZonedDateTime appointmentDateTime = ZonedDateTime.now();
        String appointmentReason = "High Fever";
        Appointment resultAppointment = appointmentService.bookAppointment(patientId,
                doctorId, appointmentDateTime, appointmentReason);
        Assert.assertEquals("book Appointment method failed", patientId, resultAppointment.getPatientId());
        Assert.assertEquals("book Appointment method failed", doctorId, resultAppointment.getDoctorId());
        Assert.assertEquals("book Appointment method failed", appointmentDateTime.toString(), resultAppointment.getAppointmentDateTime());
        Assert.assertEquals("book Appointment method failed", appointmentReason, resultAppointment.getAppointmentReason());
    }
}