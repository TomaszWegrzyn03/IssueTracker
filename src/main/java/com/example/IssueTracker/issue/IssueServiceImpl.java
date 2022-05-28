package com.example.IssueTracker.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl  implements IssueService{

    @Autowired
    private IssueRepository issueRepository;


    @Override
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public Issue getIssue(Long issue) {
        return issueRepository.findById(issue).get();
    }

    @Override
    public Issue postIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issue) {
        issueRepository.deleteById(issue);
    }

    @Override
    public Issue updateIssue(Long issueid, Issue issue) {
         Issue issues =  issueRepository.findById(issueid).get();
         issues.setIssueId(issue.getIssueId());
         issues.setIssueDesc(issue.getIssueDesc());
         issues.setIssueStatus(issue.getIssueStatus());
         issues.setIssuePriority(issue.getIssuePriority());
         issues.setIssueType(issue.getIssueType());
         issues.setIssueTitle(issue.getIssueTitle());
         issues.setProjectId(issue.getProjectId());
         issues.setIssueUsers(issue.getIssueUsers());
         return issueRepository.save(issues);
    }




}
