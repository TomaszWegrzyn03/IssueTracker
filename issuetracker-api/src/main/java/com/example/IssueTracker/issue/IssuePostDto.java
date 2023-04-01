package com.example.IssueTracker.issue;
import com.example.IssueTracker.user.SimpleUserDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Data
public final class IssuePostDto {
    private  String issueTitle;
    private  String issueDesc;
    private  String issueType;
    private  String issueStatus;
    private  String issuePriority;
    private  Long projectId;
    private List<SimpleUserDto> users;
    }


