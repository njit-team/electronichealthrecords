package com.example.premier.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.premier.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
