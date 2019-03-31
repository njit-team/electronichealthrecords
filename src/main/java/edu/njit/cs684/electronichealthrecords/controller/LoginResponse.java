package edu.njit.cs684.electronichealthrecords.controller;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {
    private String jwtToken;
    private String userName;
    private List<String> userRoles = new ArrayList<>();

    public LoginResponse(String jwtToken, UserDetails userDetails) {
        this.jwtToken = jwtToken;
        this.userName = userDetails.getUsername();
        userDetails.getAuthorities().forEach(a -> userRoles.add(a.getAuthority()));
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
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
