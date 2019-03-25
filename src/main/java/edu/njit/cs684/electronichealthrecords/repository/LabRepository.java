package edu.njit.cs684.electronichealthrecords.repository;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.LabTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends MongoRepository<LabTest, String> {

}
