package com.example.IssueTracker.issue;


import com.example.IssueTracker.project.Project;
import com.example.IssueTracker.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "issues") @Table @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, name = "issue_id")
    private Long issueId;

    @Column(nullable = false, columnDefinition = "varchar(50)", name = "issue_title")
    private String issueTitle;

    @Column(columnDefinition = "TEXT", name = "issue_desc")
    private String issueDesc;

    @Column(nullable = false, columnDefinition = "date", name = "created_at")
    private LocalDate createdAt;

    @Column(nullable = false, columnDefinition = "varchar(30)", name = "issue_type")
    private String issueType;

    @Column(nullable = false, columnDefinition = "varchar(30)", name = "issue_status")
    private String issueStatus;

    @Column(nullable = false, columnDefinition = "varchar(30)", name = "issue_priority")
    private String issuePriority;

    @JoinColumn(name = "project_id", insertable = false,updatable = false)
    @ManyToOne( fetch = FetchType.EAGER)
    @Getter(AccessLevel.NONE)
    private Project project;

    @Column(nullable = false, columnDefinition = "bigint", name = "project_id")
    private Long projectId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name="issue_users",
            joinColumns = @JoinColumn(name="issue_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> issueUsers = new ArrayList<>();




}