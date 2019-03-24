package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Appointment;
import edu.njit.cs684.electronichealthrecords.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Secured({"ROLE_RECEPTIONIST", "ROLE_DOCTOR"})
    public Appointment bookAppointment(String patientId, String doctorId,
                                       ZonedDateTime appointmentDateTime, String appointmentReason){
        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDateTime(appointmentDateTime.toString());
        appointment.setAppointmentReason(appointmentReason);
        logger.info("Booking/Saving new appointment: " + appointmentRepository);
        return appointmentRepository.save(appointment);
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_DOCTOR"})
    public Appointment viewAppointment(String appointmentId) {

        Appointment appointment;
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        appointment = optionalAppointment.orElse(null);
        return appointment;
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_DOCTOR"})
    public List<Appointment> viewAllAppointment() {
        List<Appointment> allAppointment = appointmentRepository.findAll();
        return allAppointment;
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_DOCTOR"})
    public Appointment findAppointmentByEmail(String email) {
        Appointment appointment;
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(email);
        appointment = optionalAppointment.orElse(null);
        return appointment;

    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_DOCTOR"})
    public Appointment updateAppointment(String appointmentId, ZonedDateTime appointmentNewDateTime) {
        Appointment appointment;
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        appointment = optionalAppointment.orElseThrow(() -> new RuntimeException("Appointment does not exist."));
        appointment.setAppointmentDateTime(appointmentNewDateTime.toString());
        return appointment;
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_DOCTOR"})
    public Appointment deleteAppointment(String appointmentId) {
        Appointment appointment;
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        appointment = optionalAppointment.orElseThrow(() -> new RuntimeException("Appointment does not exist."));
        appointmentRepository.deleteById(appointmentId);
        return appointment;
    }
}
