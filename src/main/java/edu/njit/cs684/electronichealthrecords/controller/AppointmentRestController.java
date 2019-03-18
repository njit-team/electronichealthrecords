package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Appointment;
import edu.njit.cs684.electronichealthrecords.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("appointment")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value = "/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment){

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

}
