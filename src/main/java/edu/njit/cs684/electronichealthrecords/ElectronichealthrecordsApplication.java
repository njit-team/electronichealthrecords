package edu.njit.cs684.electronichealthrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ElectronichealthrecordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronichealthrecordsApplication.class, args);
    }

}
