/*
 * Copyright (c) 2019.
 */

package edu.njit.cs684.electronichealthrecords.domain.restmoel;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.Role;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sulekha
 */
public class UserRest {

    @NotNull(message = "username is not sent in request")
    private String username;
    @NotNull(message = "password is not sent in request")
    private String password;
    @NotNull(message = "user role is not sent in request")
    private List<String> roles;

    public UserRest() {
    }

    public UserRest(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UserRest userToUserRest(User user) {
        UserRest userRest = new UserRest();
        userRest.setPassword("*********");
        userRest.setUsername(user.getUsername());
        List<String> roles = new ArrayList<>();
        for (Role r : user.getRoles()) {
            String role = r.getRole().substring(r.getRole().indexOf("ROLE_"));
            roles.add(role);
        }
        userRest.setRoles(roles);
        return userRest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public User userRestToUser() {

        User user = new User();
        user.setPassword(this.password);
        user.setUsername(this.username);
        List<Role> roles = new ArrayList<>();
        for (String r : this.roles) {
            Role role = new Role();
            role.setRole(r);
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }
}
