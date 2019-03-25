package edu.njit.cs684.electronichealthrecords.repository;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    List<Patient> findAll();

    Optional<Patient> findByAccountEmail(String email);

}

