package edu.njit.cs684.electronichealthrecords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("security")
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping(value = "/login")
    @Secured({"ROLE_DOCTOR", "ROLE_PATIENT", "ROLE_RECEPTIONIST"})
    public LoginResponse login(HttpSession session, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        LoginResponse loginResponse = new LoginResponse(session, userDetails);
        return loginResponse;
    }

}

class LoginResponse {
    private String jsessionid;
    private String userName;
    private List<String> userRoles = new ArrayList<>();

    public LoginResponse(HttpSession session, UserDetails userDetails) {
        this.jsessionid = session.getId();
        this.userName = userDetails.getUsername();
        userDetails.getAuthorities().forEach(a -> userRoles.add(a.getAuthority()));
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }
}