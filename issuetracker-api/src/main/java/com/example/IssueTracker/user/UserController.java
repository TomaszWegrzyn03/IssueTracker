package com.example.IssueTracker.user;

import com.example.IssueTracker.user.registration.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<SimpleUserDto> getAllSimpleUsers(){
        return userService.getAllSimpleUsers();
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest);
    }

    @GetMapping("/project/{id}")
    public List<SimpleUserDto> getUsersByProject(@PathVariable("id") Long projectId) {
        return userService.getUsersByProject(projectId).stream().map(user -> new SimpleUserDto(user.getUserId(), user.getUsername()))
                .collect(Collectors.toList());
    }
}

