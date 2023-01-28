package com.example.IssueTracker.user.registration;

import com.example.IssueTracker.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/register")

public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest registrationRequest) {
       return userService.registerUser(registrationRequest);
    }

}


