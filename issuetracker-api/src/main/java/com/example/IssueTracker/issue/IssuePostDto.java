package com.example.IssueTracker.issue;
import com.example.IssueTracker.user.SimpleUserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public record IssuePostDto(String issueTitle, String issueDesc, String issueType, String issueStatus, String issuePriority, Long projectId, List<SimpleUserDto> users)
{
}
