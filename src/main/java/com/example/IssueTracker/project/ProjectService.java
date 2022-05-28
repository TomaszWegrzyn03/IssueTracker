package com.example.IssueTracker.project;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<Project> getAllProjects();

    Project getProjectById(Long project);

    Project postProject(Project project);
}
