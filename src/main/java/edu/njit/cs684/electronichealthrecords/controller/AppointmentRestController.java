package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Appointment;
import edu.njit.cs684.electronichealthrecords.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("appointment")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value = "/book")
    public Appointment bookAppointment(@RequestBody @Validated Appointment appointment) {

        Appointment bookedAppointment;
        bookedAppointment = appointmentService.bookAppointment(appointment.getPatientId(), appointment.getDoctorId(),
                ZonedDateTime.parse(appointment.getAppointmentDateTime()), appointment.getAppointmentReason());
        return bookedAppointment;
    }

    @GetMapping(value = "/view/{appointmentId}")
    public Appointment viewAppointment(@PathVariable String appointmentId) {

        Appointment appointment;
        appointment = appointmentService.viewAppointment(appointmentId);
        return appointment;
    }

    @GetMapping(value = "/view/all")
    public List<Appointment> viewAllAppointment() {
        List<Appointment> appointments;
        appointments = appointmentService.viewAllAppointment();
        return appointments;
    }

    @PostMapping(value = "/update/{appointmentId}")
    public Appointment updateAppointment(@PathVariable String appointmentId, ZonedDateTime appointmentNewDateTime) {
        Appointment appointment;
        appointment = appointmentService.updateAppointment(appointmentId, appointmentNewDateTime);
        return appointment;

    }

    @DeleteMapping(value = "/delete/{appointmentId}")
    public Appointment deleteAppointment(@PathVariable String appointmentId) {
        Appointment appointment;
        appointment = appointmentService.deleteAppointment(appointmentId);
        return appointment;
    }

}
