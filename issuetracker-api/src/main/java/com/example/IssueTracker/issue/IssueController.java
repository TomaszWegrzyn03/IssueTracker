package com.example.IssueTracker.issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/Issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping
    public List<IssueDto> getAllSimpleIssues(){
        return issueService.getAllIssues();
    }

    @GetMapping("/{id}")
    public IssueGetDto getSimpleIssue(@PathVariable("id") Long issueId){
        return issueService.getSimpleIssue(issueId);
    }

    @GetMapping("Project/{id}")
    public List<IssueGetDto> getIssuesByProjectID(@PathVariable("id") Long projectId){
        return issueService.getIssuesByProjectId(projectId);
    }

    @PostMapping
    public Issue postIssueDto(@RequestBody IssuePostDto issuePostDto){
        return issueService.postIssue(issuePostDto);
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
