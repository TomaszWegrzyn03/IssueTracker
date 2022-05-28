package com.example.IssueTracker.issue;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueService {
    List<Issue> getAllIssues();

    Issue getIssue(Long issue);

    Issue postIssue(Issue issue);

    void deleteIssue(Long issue);

    Issue updateIssue(Long issueid, Issue issue);

}
