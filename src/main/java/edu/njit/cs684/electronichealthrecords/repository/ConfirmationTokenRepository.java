package edu.njit.cs684.electronichealthrecords.repository;


import edu.njit.cs684.electronichealthrecords.domain.dbmodel.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
