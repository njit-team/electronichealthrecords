package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.Appointment;
import edu.njit.cs684.electronichealthrecords.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointment")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Appointment bookAppointment(@RequestBody Appointment appointment){

        Appointment bookedAppointment;
        bookedAppointment = appointmentService.bookAppointment(appointment.getPatientId(), appointment.getDoctorId(),
                appointment.getAppointmentDateTime(), appointment.getAppointmentReason());
        return bookedAppointment;
    }

}
