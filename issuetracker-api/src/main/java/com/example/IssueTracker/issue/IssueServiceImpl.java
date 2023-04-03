package com.example.IssueTracker.issue;
import com.example.IssueTracker.user.SimpleUserDto;
import com.example.IssueTracker.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl  implements IssueService{

    private final IssueRepository issueRepository;
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Bean
    private ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Override
    public List<IssueDto> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream()
                .map(issue -> new IssueDto(
                        issue.getIssueId(),
                        issue.getIssueTitle(),
                        issue.getIssueDesc(),
                        issue.getIssueUsers().parallelStream()
                                .map(user -> new SimpleUserDto(user.getUserId(), user.getUsername()))
                                .collect(Collectors.toList())))
                .toList();
    }

    @Override
    public IssueGetDto getSimpleIssue(Long issueId) {
        Issue issue =  issueRepository.findById(issueId).get();
        List<SimpleUserDto> simpleUsers = issue.getIssueUsers()
                .stream()
                .map(user -> modelMapper().map(user, SimpleUserDto.class)).collect(Collectors.toList());
        return new IssueGetDto(
                issue.getIssueId(),
                issue.getIssueTitle(),
                issue.getIssueDesc(),
                issue.getIssueType(),
                issue.getIssueStatus(),
                issue.getIssuePriority(),
                issue.getProjectId(),
                simpleUsers);
    }

    @Override
    public Issue postIssue(IssuePostDto issuePostDto) {
        List<User> users = issuePostDto.getUsers().stream().map(user ->modelMapper().map(user, User.class))
                .collect(Collectors.toList());
        LocalDate createdAt = LocalDate.now();
        Issue issue = new Issue(
                issuePostDto.getIssueTitle(),
                issuePostDto.getIssueDesc(),
                issuePostDto.getIssueType(),
                issuePostDto.getIssueStatus(),
                issuePostDto.getIssuePriority(),
                issuePostDto.getProjectId(),
                createdAt,
                users);

        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issue) {
        issueRepository.deleteById(issue);
    }

    @Override
    public Issue updateIssue(Long issueid, Issue issue) {
        Issue issueToUpdate =  issueRepository.findById(issueid).get();
        issueToUpdate.setIssueId(issue.getIssueId());
        issueToUpdate.setIssueDesc(issue.getIssueDesc());
        issueToUpdate.setIssueStatus(issue.getIssueStatus());
        issueToUpdate.setIssuePriority(issue.getIssuePriority());
        issueToUpdate.setIssueType(issue.getIssueType());
        issueToUpdate.setIssueTitle(issue.getIssueTitle());
        issueToUpdate.setProjectId(issue.getProjectId());
        issueToUpdate.setIssueUsers(issue.getIssueUsers());
         return issueRepository.save(issueToUpdate);
    }

    @Override
    public List<IssueGetDto> getIssuesByProjectId(Long projectId) {
        return issueRepository.findIssueByProjectId(projectId).stream()
                .map(issue -> new IssueGetDto(
                        issue.getIssueId(),
                        issue.getIssueTitle(),
                        issue.getIssueDesc(),
                        issue.getIssueStatus(),
                        issue.getIssuePriority(),
                        issue.getIssueType(),
                        issue.getProjectId(),
                        issue.getIssueUsers().parallelStream()
                                .map(user -> new SimpleUserDto(user.getUserId(), user.getUsername()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
