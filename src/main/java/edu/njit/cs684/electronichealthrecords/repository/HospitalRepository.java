package edu.njit.cs684.electronichealthrecords.repository;


import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    List<Hospital> findAll();

    Hospital findByEmailIgnoreCase(String email);

}
