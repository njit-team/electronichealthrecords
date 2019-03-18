/*
 * Copyright (c) 2018.
 */

package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.dbmodel.security.User;
import edu.njit.cs684.electronichealthrecords.domain.restmoel.UserRest;
import edu.njit.cs684.electronichealthrecords.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author sulekha
 * njit_cs_684_electronichealthrecords, 2019
 */
@RestController
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-up", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public UserRest signUp(@RequestBody UserRest userRest) {
        User user = userRest.userRestToUser();
        User savedUser = userService.signUp(user);
        return UserRest.userToUserRest(savedUser);
    }

    @GetMapping(value = "/delete-user/{username}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserRest deleteUser(@PathVariable String username) {
        User deletedUser = userService.deleteUser(username);
        return UserRest.userToUserRest(deletedUser);
    }
}