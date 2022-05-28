package com.example.IssueTracker.project;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/Projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private static ModelMapper modelMapper = new ModelMapper();


    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }
    @GetMapping("/simple")
    public List<ProjectDto> getAllProjectsDto(){
       return projectService.getAllProjects().stream()
               .map(project -> new ProjectDto(project.getProjectId(), project.getTitle(), project.getDescription(), project.getCreatedAt())).toList();

    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long project){
        return projectService.getProjectById(project);
    }

    @PostMapping
    public Project postProject(@RequestBody Project project ){
        return projectService.postProject(project);
    }


}
