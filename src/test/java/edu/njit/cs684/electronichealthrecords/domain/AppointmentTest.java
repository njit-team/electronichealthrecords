package edu.njit.cs684.electronichealthrecords.domain;

import org.junit.*;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AppointmentTest {

    Appointment appointment;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        appointment = new Appointment();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAppointmentId() {
        Assert.assertNull(appointment.getAppointmentId());
    }

    @Test
    public void setAppointmentId() {
        String sampleId = UUID.randomUUID().toString();
        appointment.setAppointmentId(sampleId);
        String result = appointment.getAppointmentId();
        Assert.assertEquals("setAppointmentId method failed", sampleId, result);
    }

    @Test
    public void getPatientId() {
        Assert.assertNull(appointment.getPatientId());
    }

    @Test
    public void setPatientId() {
        String sampleId = UUID.randomUUID().toString();
        appointment.setPatientId(sampleId);
        String result = appointment.getPatientId();
        Assert.assertEquals("setPatientId method failed", sampleId, result);
    }

    @Test
    public void getDoctorId() {
        Assert.assertNull(appointment.getDoctorId());
    }

    @Test
    public void setDoctorId() {
        String sampleId = UUID.randomUUID().toString();
        appointment.setDoctorId(sampleId);
        String result = appointment.getDoctorId();
        Assert.assertEquals("setDoctorId method failed", sampleId, result);
    }

    @Test
    public void getAppointmentDateTime() {
        Assert.assertNull(appointment.getAppointmentDateTime());
    }

    @Test
    public void setAppointmentDateTime() {
        ZonedDateTime dateTime = ZonedDateTime.now();
        appointment.setAppointmentDateTime(dateTime);
        ZonedDateTime result = appointment.getAppointmentDateTime();
        Assert.assertEquals("getAppointmentDateTime method failed", dateTime, result);
    }

    @Test
    public void getAppointmentReason() {
        Assert.assertNull(appointment.getAppointmentReason());
    }

    @Test
    public void setAppointmentReason() {
        String reason = "High Fever";
        appointment.setAppointmentReason(reason);
        String result = appointment.getAppointmentReason();
        Assert.assertEquals("setAppointmentReason method failed", reason, result);
    }
}