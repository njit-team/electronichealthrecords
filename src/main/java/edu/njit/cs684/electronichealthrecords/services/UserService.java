package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.User;
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
    public User signUp(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = applicationUserRepository.save(user);
        return savedUser;
    }

    @Secured("ROLE_ADMIN")
    public User deleteUser(String username) {
        User deletedUsername = applicationUserRepository.findByUsername(username);
        if (Objects.isNull(deletedUsername)) {
            throw new RuntimeException("User does not exist." + username);
        }
        applicationUserRepository.deleteById(deletedUsername.getId());
        logger.info("Deleted User:" + deletedUsername);
        return deletedUsername;
    }

    @Secured({"ROLE_PATIENT", "ROLE_DOCTOR"})
    public User viewMedicalHistory(String patientId) {
        User user;
        Optional<User> optionalUser = applicationUserRepository.findById(patientId);
        user = optionalUser.orElse(null);
        return user;
    }







}
