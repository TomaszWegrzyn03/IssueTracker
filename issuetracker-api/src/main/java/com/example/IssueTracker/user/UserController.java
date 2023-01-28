package com.example.IssueTracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/project/{id}")
    public List<SimpleUserDto> getUsersByProject(@PathVariable("id") Long projectId) {
        return userService.getUsersByProject(projectId).stream().map(user -> new SimpleUserDto(user.getUserId(), user.getUsername()))
                .collect(Collectors.toList());
    }
}

