package com.example.IssueTracker.project;


import com.example.IssueTracker.issue.Issue;
import com.example.IssueTracker.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "projects") @Table @AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, name = "project_id")
    private Long projectId;

    @Column(nullable = false, columnDefinition = "varchar(50)", name = "title")
    private String title;

    @Column(columnDefinition = "TEXT", name = "description")
    private String description;

    @Column(nullable = false, columnDefinition = "date", name = "created_at")
    private LocalDate createdAt;

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    private List<Issue> issues;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="project_users",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    @ToString.Exclude
    private List<User> projectUsers = new ArrayList<>();

}
