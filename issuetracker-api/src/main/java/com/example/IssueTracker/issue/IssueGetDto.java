package com.example.IssueTracker.issue;
import com.example.IssueTracker.user.SimpleUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class IssueGetDto {
    private Long issueId;
    private String issueTitle;
    private String issueDesc;
    private String issueType;
    private String issueStatus;
    private String issuePriority;
    private Long projectId;
    private List<SimpleUserDto> users;

}
