package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.Appointment;
import edu.njit.cs684.electronichealthrecords.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("appointment")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Appointment bookAppointment(@RequestBody Appointment appointment){

        Appointment bookedAppointment;
        bookedAppointment = appointmentService.bookAppointment(appointment.getPatientId(), appointment.getDoctorId(),
                ZonedDateTime.parse(appointment.getAppointmentDateTime()), appointment.getAppointmentReason());
        return bookedAppointment;
    }

    @RequestMapping(value = "/view/{appointmentId}", method = RequestMethod.GET)
    public Appointment viewAppointment(@PathVariable String appointmentId) {

        Appointment appointment;
        appointment = appointmentService.viewAppointment(appointmentId);
        return appointment;
    }

}
