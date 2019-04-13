package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.configuration.TokenProvider;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/generate")
//    @Secured({"ROLE_DOCTOR", "ROLE_PATIENT", "ROLE_RECEPTIONIST"})
    public LoginResponse register(@RequestBody User loginUser) throws AuthenticationException {

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getUsername());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        LoginResponse loginResponse = new LoginResponse(token, userDetails);
        return loginResponse;
    }

    @GetMapping(value = "/role")
    public Collection<GrantedAuthority> returnRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        User user = null;
        if (principal instanceof User) {
            user = (User) principal;
        }
        return user.getAuthorities();
    }

}
