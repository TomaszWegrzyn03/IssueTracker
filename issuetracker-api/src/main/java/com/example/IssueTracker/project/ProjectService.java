package com.example.IssueTracker.project;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProjectService {
    List<ProjectGetDto> getAllProjects();

    List<ProjectDto> getAllSimpleProjects();

    Project getProjectById(Long project);

    ProjectPostDto postProject(ProjectPostDto projectPostDto);

    List<ProjectDto> getAllUserProjects(String username);

    void deleteProject(Long projectId);
}
