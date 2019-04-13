/*
 * Copyright (c) 2019.
 */

package edu.njit.cs684.electronichealthrecords.services;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.AppUser;
import edu.njit.cs684.electronichealthrecords.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author sulekha
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = applicationUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(),
                appUser.getRoles());
    }


}
