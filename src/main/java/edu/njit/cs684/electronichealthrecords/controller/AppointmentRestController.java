package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.controller.response.ErrorResponse;
import edu.njit.cs684.electronichealthrecords.controller.response.Violation;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Appointment;
import edu.njit.cs684.electronichealthrecords.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("appointment")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ErrorResponse error = new ErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    @PostMapping(value = "/book-appointment")
    public Appointment bookAppointment(@RequestBody @Validated Appointment appointment) {

        Appointment bookedAppointment;
        bookedAppointment = appointmentService.bookAppointment(appointment.getPatientId(), appointment.getDoctorId(),
                ZonedDateTime.parse(appointment.getAppointmentDateTime()), appointment.getAppointmentReason());
        return bookedAppointment;
    }

    @GetMapping(value = "/view-appointment/{appointmentId}")
    public Appointment viewAppointment(@PathVariable String appointmentId) {

        Appointment appointment;
        appointment = appointmentService.viewAppointment(appointmentId);
        return appointment;
    }

    @PostMapping(value = "/update-appointment/{appointmentId}")
    public Appointment updateAppointment(@PathVariable String appointmentId, ZonedDateTime appointmentNewDateTime) {
        Appointment appointment;
        appointment = appointmentService.updateAppointment(appointmentId, appointmentNewDateTime);
        return appointment;

    }

    @DeleteMapping(value = "/delete-appointment/{appointmentId}")
    public Appointment deleteAppointment(@PathVariable String appointmentId) {
        Appointment appointment;
        appointment = appointmentService.deleteAppointment(appointmentId);
        return appointment;
    }

}
