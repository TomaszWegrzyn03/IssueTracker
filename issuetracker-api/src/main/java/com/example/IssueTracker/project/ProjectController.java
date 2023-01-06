package com.example.IssueTracker.project;


import com.example.IssueTracker.user.SimpleUserDto;
import com.example.IssueTracker.user.User;
import com.example.IssueTracker.user.UserRepository;
import com.example.IssueTracker.user.UserService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/Projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;


    private static ModelMapper modelMapper = new ModelMapper();


    @GetMapping
    public List<ProjectTestDto> getAllProjects(){
        return projectService.getAllProjects().stream()
                .map(project -> new ProjectTestDto(project.getProjectId(),project.getTitle(),
                        project.getProjectUsers().stream().map(user -> new SimpleUserDto(user.getUserId(), user.getUsername())).toList())).collect(Collectors.toList());
    }

    @GetMapping("/simple")
    public List<ProjectDto> getAllProjectsDto(){
       return projectService.getAllProjects().stream()
               .map(project -> new ProjectDto(project.getProjectId(), project.getTitle(), project.getDescription(), project.getCreatedAt())).toList();

    }

    @GetMapping("/userSimpleProjects")
    public List<ProjectDto> getAllUserProjects( @org.jetbrains.annotations.NotNull Authentication authentication){
            String username = authentication.getName();
        return projectService.getAllUserProjects(username).stream()
                .map(project -> new ProjectDto(project.getProjectId(), project.getTitle(), project.getDescription(), project.getCreatedAt())).toList();

    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long project){
        return projectService.getProjectById(project);
    }

    @PostMapping
    public ProjectPostDto postProject(@RequestBody ProjectPostDto projectPostDto ){
        Project project = new Project();
        List<User> users = new ArrayList<>();
        List<SimpleUserDto> simpleUsers = projectPostDto.getProjectUsers();
        for(SimpleUserDto simpleUser : simpleUsers){
        Optional<User> user = userRepository.findByUsername(simpleUser.getUsername());
        users.add(user.get());
        }
        project.setProjectUsers(users);
        project.setTitle(projectPostDto.getTitle());
        project.setDescription(projectPostDto.getDescription());
        project.setCreatedAt(LocalDate.now());
        project.setIssues(null);
        projectService.postProject(project);
        return projectPostDto;
    }


}
