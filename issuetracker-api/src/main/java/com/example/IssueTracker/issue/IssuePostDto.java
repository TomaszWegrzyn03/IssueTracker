package com.example.IssueTracker.issue;


import com.example.IssueTracker.user.SimpleUserDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssuePostDto {

    private String issueTitle;

    private String issueDesc;

    private String issueType;

    private String issueStatus;

    private String issuePriority;

    private Long projectId;

    private List<SimpleUserDto> users;

}
