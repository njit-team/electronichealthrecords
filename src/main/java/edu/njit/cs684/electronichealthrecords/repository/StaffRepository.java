package edu.njit.cs684.electronichealthrecords.repository;


import edu.njit.cs684.electronichealthrecords.domain.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String> {
    List<Staff> findAll();

}
