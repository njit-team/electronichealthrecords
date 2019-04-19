/*
 * Copyright (c) 2019.
 */

package edu.njit.cs684.electronichealthrecords.repository;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author sulekha
 */
public interface ApplicationUserRepository extends MongoRepository<AppUser, String> {
    AppUser findByUsername(String userName);

    Long countByUserType(String userType);
}
