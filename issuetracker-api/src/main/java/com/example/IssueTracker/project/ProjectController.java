package com.example.IssueTracker.project;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/Projects")
public class ProjectController {

    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectGetDto> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/simple")
    public List<ProjectDto> getAllSimpleProjects(){
       return projectService.getAllSimpleProjects();
    }

    @GetMapping("/userSimpleProjects")
    public List<ProjectDto> getAllUserProjects(@org.jetbrains.annotations.NotNull Authentication authentication){
            String username = authentication.getName();
        return projectService.getAllUserProjects(username);
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long project){
        return projectService.getProjectById(project);
    }

    @PostMapping
    public ProjectPostDto postProject(@RequestBody ProjectPostDto projectPostDto ){
        return projectService.postProject(projectPostDto);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable("id") Long projectId){
        projectService.deleteProject(projectId);
        return "Project" + projectId + "deleted successfully";
    }

}
