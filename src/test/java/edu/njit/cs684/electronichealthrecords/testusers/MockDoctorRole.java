package edu.njit.cs684.electronichealthrecords.testusers;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(value = "doctor", roles = "DOCTOR")
public @interface MockDoctorRole {
}
