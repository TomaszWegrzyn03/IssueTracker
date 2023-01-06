package com.example.IssueTracker.issue;

import com.example.IssueTracker.user.SimpleUserDto;
import com.example.IssueTracker.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/Issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    private static ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<Issue> getAllIssues(){
        return issueService.getAllIssues();
    }

    @GetMapping(path = "/simple" )
    public List<IssueDto> getAllSimpleIssues(){
        return issueService.getAllIssues().stream()
                .map(issue -> new IssueDto(issue.getIssueId(), issue.getIssueTitle(), issue.getIssueDesc(),
                        issue.getIssueUsers().parallelStream()
                                .map(user -> new SimpleUserDto(user.getUserId(), user.getUsername()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable("id") Long issue){
        return issueService.getIssue(issue);
    }

    @GetMapping("Project/{id}")
    public List<IssuePostDto> getIssuesByProjectID(@PathVariable("id") Long projectId){
        return issueService.getIssuesByProjectId(projectId).stream()
                .map(issue -> new IssuePostDto(
                        issue.getIssueTitle(),
                        issue.getIssueDesc(),
                        issue.getIssueStatus(),
                        issue.getIssuePriority(),
                        issue.getIssueType(),
                        issue.getIssueId(),
                        issue.getIssueUsers().parallelStream()
                                .map(user -> new SimpleUserDto(user.getUserId(), user.getUsername()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/simple")
    public Issue postIssueDto(@RequestBody IssuePostDto issuePostDto){
        Issue issue = new Issue();
        issue.setIssueTitle(issuePostDto.getIssueTitle());
        issue.setIssueDesc(issuePostDto.getIssueDesc());
        issue.setIssueType(issuePostDto.getIssueType());
        issue.setIssueStatus(issuePostDto.getIssueStatus());
        issue.setIssuePriority(issuePostDto.getIssuePriority());
        issue.setProjectId(issuePostDto.getProjectId());
        issue.setCreatedAt(LocalDate.now());
        List<SimpleUserDto> userDto = issuePostDto.getUsers();
        List<User> users = userDto.stream()
                .map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
        issue.setIssueUsers(users);

        return issueService.postIssue(issue);
    }



    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable("id") Long issue){
        issueService.deleteIssue(issue);
    }



    @PutMapping("/{id}")
    public Issue updateIssue(@PathVariable("id") Long issueid, @RequestBody Issue issue){
        return issueService.updateIssue(issueid,issue);
    }
}
