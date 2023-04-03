package com.example.IssueTracker.project;
import com.example.IssueTracker.issue.IssueRepository;
import com.example.IssueTracker.user.SimpleUserDto;
import com.example.IssueTracker.user.User;
import com.example.IssueTracker.user.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.issueRepository = issueRepository;
    }

    @Override
    public List<ProjectGetDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(project -> new ProjectGetDto(
                        project.getProjectId(),
                        project.getTitle(),
                        project.getProjectUsers()
                                .stream()
                                .map(user -> new SimpleUserDto(user.getUserId(), user.getUsername()))
                                .toList())).toList();
    }
    @Override
    public List<ProjectDto> getAllSimpleProjects() {
        return projectRepository.findAll().stream()
                .map(project -> new ProjectDto(
                        project.getProjectId(),
                        project.getTitle(),
                        project.getDescription(),
                        project.getCreatedAt()))
                .toList();
    }

    @Override
    public Project getProjectById(Long project) {
        return projectRepository.getById(project);
    }

    @Override
    public ProjectPostDto postProject(ProjectPostDto projectPostDto) {
        List<User> users = new ArrayList<>();
        List<SimpleUserDto> simpleUsers = projectPostDto.getProjectUsers();
        for(SimpleUserDto simpleUser : simpleUsers){
            User user = userRepository.findByUsername(simpleUser.getUsername()).orElse(null);
            users.add(user);
        }
        Project project = new Project(
                null,
                projectPostDto.getTitle(),
                projectPostDto.getDescription(),
                LocalDate.now(),
                null,
                users );
        projectRepository.save(project);
        return projectPostDto;
    }

    @Override
    public List<ProjectDto> getAllUserProjects(String username){
        return projectRepository.findByProjectUsers_Username(username).
                stream()
                .map(project -> new ProjectDto(
                        project.getProjectId(),
                        project.getTitle(),
                        project.getDescription(),
                        project.getCreatedAt()))
                .toList();
    }

    @Override
    public void deleteProject(Long projectId) {
        issueRepository.deleteAllIssuesByProjectId(projectId);
        projectRepository.deleteProject(projectId);

    }
}
