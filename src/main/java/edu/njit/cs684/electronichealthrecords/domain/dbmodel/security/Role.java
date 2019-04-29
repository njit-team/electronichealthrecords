package edu.njit.cs684.electronichealthrecords.domain.dbmodel.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements GrantedAuthority {

    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (Objects.isNull(role)) {
            throw new IllegalArgumentException("Role can not be null.");
        }
        role = role.toUpperCase();
        if (!role.startsWith("ROLE_")) {
            role = String.format("ROLE_%s", role);
        }
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }
}
