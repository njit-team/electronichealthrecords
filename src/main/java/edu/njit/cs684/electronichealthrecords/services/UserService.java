package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.AppUser;
import edu.njit.cs684.electronichealthrecords.repository.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(ApplicationUserRepository applicationUserRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Secured("ROLE_ANONYMOUS")
    public AppUser signUp(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        AppUser savedAppUser = applicationUserRepository.save(appUser);
        return savedAppUser;
    }

    @Secured({"ROLE_ADMIN", "ROLE_DOCTOR"})
    public AppUser deleteUser(String username) {
        AppUser deletedUsername = applicationUserRepository.findByUsername(username);
        if (Objects.isNull(deletedUsername)) {
            throw new RuntimeException("AppUser does not exist." + username);
        }
        applicationUserRepository.deleteById(deletedUsername.getId());
        logger.info("Deleted AppUser:" + deletedUsername);
        return deletedUsername;
    }

    @Secured({"ROLE_PATIENT", "ROLE_DOCTOR"})
    public AppUser viewMedicalHistory(String patientId) {
        AppUser appUser;
        Optional<AppUser> optionalUser = applicationUserRepository.findById(patientId);
        appUser = optionalUser.orElse(null);
        return appUser;
    }

    @Secured("ROLE_ANONYMOUS")
    public Long countByUserType(String userType) {
        Long numberOfUsers = applicationUserRepository.countByUserType(userType);
        return numberOfUsers;
    }
}
