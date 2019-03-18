package edu.njit.cs684.electronichealthrecords.repository;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {


}
