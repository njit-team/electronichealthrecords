package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Appointment;
import edu.njit.cs684.electronichealthrecords.repository.AppointmentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    public void bookAppointment() {
        Assert.assertNotNull(appointmentService);
        String patientId = "SD12";
        String doctorId = "DOC21";
        ZonedDateTime appointmentDateTime = ZonedDateTime.now();
        String appointmentReason = "High Fever";
        Appointment resultAppointment = appointmentService.bookAppointment(patientId,
                doctorId, appointmentDateTime, appointmentReason);
        Assert.assertEquals("patientId Appointment method failed", patientId, resultAppointment.getPatientId());
        Assert.assertEquals("doctorId Appointment method failed", doctorId, resultAppointment.getDoctorId());
        Assert.assertEquals("book Appointment method failed", appointmentDateTime.toString(), resultAppointment.getAppointmentDateTime());
        Assert.assertEquals("book Appointment method failed", appointmentReason, resultAppointment.getAppointmentReason());
    }

    @Test
    public void updateAppointment() {
        Assert.assertNotNull(appointmentService);
        String patientId = "SD12";
        String doctorId = "DOC23";
        ZonedDateTime appointmentDateTime = ZonedDateTime.now();
        String appointmentReason = "Lasering";
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime appointmentNewDatetime = ZonedDateTime.of(2019, 5, 12, 6,
                20, 30, 200, zoneId);

        Appointment resultAppointment = appointmentService.bookAppointment(patientId, doctorId,
                appointmentDateTime, appointmentReason);
        String appointmentId = resultAppointment.getAppointmentId();

        Appointment resultUpdatedAppointment = appointmentService.updateAppointment(appointmentId, appointmentNewDatetime);

        Assert.assertEquals("updateAppointment method failed", appointmentNewDatetime.toString(), resultUpdatedAppointment.getAppointmentDateTime());

        appointmentRepository.deleteById(patientId);


    }
}