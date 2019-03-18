/*
 * Copyright (c) 2019.
 */

package edu.njit.cs684.electronichealthrecords.repository;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author sulekha
 */
public interface ApplicationUserRepository extends MongoRepository<User, String> {
    User findByUsername(String userName);
}
