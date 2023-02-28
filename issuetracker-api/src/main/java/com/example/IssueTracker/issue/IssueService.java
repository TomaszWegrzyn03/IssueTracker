package com.example.IssueTracker.issue;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IssueService {
    List<IssueDto> getAllIssues();

    Issue postIssue(IssuePostDto issuePostDto);

    Issue updateIssue(Long issueid, Issue issue);

    List<IssueGetDto> getIssuesByProjectId(Long projectId);

    IssueGetDto getSimpleIssue(Long issueId);

    void deleteIssue(Long issueId);
}
