package com.example.IssueTracker.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long project) {
        return projectRepository.findById(project).get();
    }

    @Override
    public Project postProject(Project project) {
        return projectRepository.save(project);
    }
}
