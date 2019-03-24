package edu.njit.cs684.electronichealthrecords.repository;


import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String> {
    List<Staff> findAll();

}
