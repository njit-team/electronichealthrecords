package com.example.premier.repository;

import com.example.premier.model.Hospital;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    List<Hospital> findAll();

    Hospital findByEmailIgnoreCase(String email);

}
